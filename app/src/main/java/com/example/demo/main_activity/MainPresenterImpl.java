package com.example.demo.main_activity;


import android.util.Log;
import android.widget.Button;

import com.example.demo.model.NoticeList;

import java.util.ArrayList;

/**
 * Created by bpn on 12/7/17.
 */

public class MainPresenterImpl implements MainContract.presenter, MainContract.GetNoticeIntractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetNoticeIntractor getNoticeIntractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetNoticeIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }


    @Override
    public void requestDataFromServer() {
        if (mainView != null) {
            mainView.showProgress();
        }
        getNoticeIntractor.getNoticeArrayList(this);
    }

    @Override
    public void getTotalAmount(String amount, int Count) {
        int totalAmount = Integer.parseInt(amount) * Count;
        if(mainView!=null){
            mainView.updateAmount(String.valueOf(totalAmount));
        }
    }


    @Override
    public void onFinished(NoticeList noticeArrayList) {
        if (mainView != null) {

            mainView.setDataToView(noticeArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(String t) {
        if (mainView != null) {
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }


}
