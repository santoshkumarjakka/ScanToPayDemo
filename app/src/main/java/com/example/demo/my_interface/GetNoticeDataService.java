package com.example.demo.my_interface;


import com.example.demo.model.NoticeList;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetNoticeDataService {

    @GET("{prestodb}/{presto}/issues")
    Call<ArrayList<NoticeList>> getNoticeData(@Path("prestodb") String prestodb ,@Path("presto") String presto ,@Query("state") String param1);

}