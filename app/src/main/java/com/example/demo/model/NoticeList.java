
package com.example.demo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NoticeList implements Parcelable{

    @SerializedName("product-name")
    @Expose
    private String productName;
    @SerializedName("product-image-url")
    @Expose
    private String productImageUrl;
    @SerializedName("product-Price")
    @Expose
    private String productPrice;
    @SerializedName("product-id")
    @Expose
    private Integer productId;
    @SerializedName("product-desc")
    private String productDesc;

    protected NoticeList(Parcel in) {
        productName = in.readString();
        productImageUrl = in.readString();
        productPrice = in.readString();
        if (in.readByte() == 0) {
            productId = null;
        } else {
            productId = in.readInt();
        }
        productDesc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(productImageUrl);
        dest.writeString(productPrice);
        if (productId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(productId);
        }
        dest.writeString(productDesc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NoticeList> CREATOR = new Creator<NoticeList>() {
        @Override
        public NoticeList createFromParcel(Parcel in) {
            return new NoticeList(in);
        }

        @Override
        public NoticeList[] newArray(int size) {
            return new NoticeList[size];
        }
    };

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
