package com.example.demo.addtocart;


import android.util.Log;
import android.widget.Button;

import com.example.demo.main_activity.MainContract;
import com.example.demo.model.NoticeList;

import java.util.ArrayList;

/**
 * Created by bpn on 12/7/17.
 */

public class AddPresenterImpl implements MainContract.addPresenter {

    private MainContract.cartView mainView;
    private MainContract.GetNoticeIntractor getNoticeIntractor;
    NoticeList noticeList;

    public AddPresenterImpl(MainContract.cartView mainView, NoticeList noticeList) {
        this.mainView = mainView;
        this.noticeList = noticeList;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public NoticeList requestData() {
        return noticeList;
    }

    @Override
    public void getTotalAmount(String amount, int Count) {
        int totalAmount = Integer.parseInt(amount) * Count;
        if (mainView != null) {
            mainView.updateAmount(String.valueOf(totalAmount));
        }
    }


}
