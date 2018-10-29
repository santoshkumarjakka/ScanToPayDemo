package com.example.demo.invoice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.main_activity.UserInputActivity;
import com.example.demo.model.NoticeList;

public class InvoiceActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Invoice");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                finishAffinity();
                finish();
                startActivity(new Intent(InvoiceActivity.this, UserInputActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        NoticeList addToCartModel = getIntent().getParcelableExtra("addtocart");
        ((TextView) findViewById(R.id.tvProductName)).setText(addToCartModel.getProductName());
        ((TextView) findViewById(R.id.tvProductAmount)).setText("Rs " + addToCartModel.getProductPrice());
        ((TextView) findViewById(R.id.tvProductTotalAmount)).setText("Rs " + addToCartModel.getProductPrice());

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onClickHome(View view) {
        finishAffinity();
        finish();
        startActivity(new Intent(InvoiceActivity.this, UserInputActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
}
