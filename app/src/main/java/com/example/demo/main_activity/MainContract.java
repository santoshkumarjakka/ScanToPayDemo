package com.example.demo.main_activity;

import android.content.Context;
import android.widget.Button;

import com.example.demo.model.NoticeList;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bpn on 12/6/17.
 */

public interface MainContract {

    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */
    interface presenter{

        void onDestroy();

        void requestDataFromServer();

        void getTotalAmount(String amount, int Count);

    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetNoticeInteractorImpl class
     **/
    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToView(NoticeList noticeArrayList);

        void onResponseFailure(String throwable);

        boolean checkNetworkConection();
        Context getContext();
        void updateAmount(String amount);


    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetNoticeIntractor {

        interface OnFinishedListener {
            void onFinished(NoticeList noticeArrayList);
            void onFailure(String t);
        }

        void getNoticeArrayList(OnFinishedListener onFinishedListener);
    }
    interface addPresenter{

        void onDestroy();

        NoticeList requestData();

        void getTotalAmount(String amount, int Count);

    }
    interface cartView {

        void updateAmount(String amount);


    }

}
