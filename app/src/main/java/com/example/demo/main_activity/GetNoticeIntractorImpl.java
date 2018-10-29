package com.example.demo.main_activity;

import android.content.Context;
import android.util.Log;


import com.example.demo.model.NoticeList;
import com.example.demo.my_interface.GetNoticeDataService;
import com.example.demo.network.RetrofitInstance;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.NotSerializableException;
import java.util.ArrayList;

/**
 * Created by bpn on 12/7/17.
 */

public class GetNoticeIntractorImpl implements MainContract.GetNoticeIntractor {
    private Context context;

    public GetNoticeIntractorImpl(Context context) {
        this.context = context;
    }


    @Override
    public void getNoticeArrayList(final OnFinishedListener onFinishedListener) {
        Gson gson = new Gson();
        NoticeList list = gson.fromJson(loadJSONFromAsset(context), NoticeList.class);
        onFinishedListener.onFinished(list);
    }

    private String loadJSONFromAsset(Context context) {
        String json = null;
        try {

            InputStream is = context.getAssets().open("product.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
