package com.example.demo.main_activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.demo.addtocart.AddToCartActivity;
import com.example.demo.R;
import com.example.demo.model.NoticeList;

public class MainActivity extends AppCompatActivity implements MainContract.MainView, View.OnClickListener {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private MainContract.presenter presenter;
    private NoticeList addToCartModel;
    private ImageView ivProductImage;
    private TextView txt_product_title, txt_product_amount, txt_product_desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl(this, new GetNoticeIntractorImpl(MainActivity.this));
        initializeToolbarAndView();
        initProgressBar(MainActivity.this);
        getPresentCall();

    }

    private void getPresentCall() {
        presenter.requestDataFromServer();
    }


    /**
     * Initializing Toolbar and RecyclerView
     */
    private void initializeToolbarAndView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivProductImage = findViewById(R.id.ivProductImage);
        txt_product_title = findViewById(R.id.txt_product_title);
        txt_product_amount = findViewById(R.id.txt_product_amount);
        txt_product_desc = findViewById(R.id.txt_product_desc);
        findViewById(R.id.btnAddToCart).setOnClickListener(this);


    }


    /**
     * Initializing progressbar programmatically
     */
    public ProgressBar initProgressBar(Context context) {
        progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        ((Activity) context).addContentView(relativeLayout, params);
        return progressBar;
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void setDataToView(NoticeList noticeArrayList) {
        addToCartModel = noticeArrayList;
        Glide.with(MainActivity.this).load(noticeArrayList.getProductImageUrl()).placeholder(R.drawable.loading).error(R.drawable.no_image_avalible).into(ivProductImage);
        txt_product_title.setText(noticeArrayList.getProductName());
        txt_product_amount.setText("Rs " + noticeArrayList.getProductPrice());
        txt_product_desc.setText(noticeArrayList.getProductDesc());
    }

    @Override
    public void onResponseFailure(String throwable) {
    }

    @Override
    public boolean checkNetworkConection() {
        ConnectivityManager ConnectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ConnectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {

            return true;
        } else {
            Toast.makeText(MainActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public Context getContext() {
        return MainActivity.this;
    }

    @Override
    public void updateAmount(String amount) {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    @Override
    public void onClick(View v) {
        getPresentCall();


    }


    public void onClickPayAmount(View view) {
        if (addToCartModel != null) {
            startActivity(new Intent(MainActivity.this, AddToCartActivity.class).putExtra("addtocart", addToCartModel));
        }
    }
}

