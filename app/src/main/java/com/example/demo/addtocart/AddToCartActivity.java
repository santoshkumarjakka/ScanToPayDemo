package com.example.demo.addtocart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.demo.R;
import com.example.demo.invoice.InvoiceActivity;
import com.example.demo.main_activity.MainContract;
import com.example.demo.model.NoticeList;

public class AddToCartActivity extends AppCompatActivity implements MainContract.cartView, View.OnClickListener {

    private ImageView ivProductImage;
    private TextView txt_product_title, txt_product_amount, tvQuanty;
    private Button btnPay;
    private FloatingActionButton fabMinus, fabPlus;
    private AddPresenterImpl addPresenter;
    private NoticeList noticeList;
    private int count = 1;
    private String totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        addPresenter = new AddPresenterImpl(this, (NoticeList) getIntent().getParcelableExtra("addtocart"));
        initializeView();
        updateView();


    }

    private void updateView() {
        noticeList = addPresenter.requestData();
        tvQuanty.setText("1");
        txt_product_amount.setText("Rs  " + noticeList.getProductPrice());
        txt_product_title.setText(noticeList.getProductName());
        updateAmount(noticeList.getProductPrice());
        Glide.with(AddToCartActivity.this).load(noticeList.getProductImageUrl()).placeholder(R.drawable.loading).error(R.drawable.no_image_avalible).into(ivProductImage);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void updateAmount(String amount) {
        totalAmount = amount;
        ((Button) findViewById(R.id.btnPay)).setText(getResources().getString(R.string.pay) + " " + "Rs " + amount);

    }

    @SuppressLint("RestrictedApi")
    private void initializeView() {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Add to Cart");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivProductImage = findViewById(R.id.ivProductImage);
        txt_product_title = findViewById(R.id.txt_notice_title);
        txt_product_amount = findViewById(R.id.txt_notice_brief);
        tvQuanty = findViewById(R.id.tvQuanty);
        fabMinus = findViewById(R.id.fabMinus);
        fabPlus = findViewById(R.id.fabPlus);
        fabMinus.setOnClickListener(this);
        fabPlus.setOnClickListener(this);

    }

    public void onClickAmount(View view) {
        if (noticeList != null) {
            noticeList.setProductPrice(totalAmount);
            startActivity(new Intent(AddToCartActivity.this, InvoiceActivity.class).putExtra("addtocart", noticeList));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fabMinus) {
            if (count != 1) {
                count--;
                tvQuanty.setText(String.valueOf(count));
                addPresenter.getTotalAmount(noticeList.getProductPrice(), count);
            }
        } else if (v.getId() == R.id.fabPlus) {
            count++;
            tvQuanty.setText(String.valueOf(count));
            addPresenter.getTotalAmount(noticeList.getProductPrice(), count);
        }
    }
}
