package com.truesight.Retrofit;

import com.truesight.Model.VideoDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("search")                                      //method retrofit yang dipakai utk request data
    Call<VideoDetails> getVideoData(                  //"search" untuk mencari video yang berada di dalam chanel untuk menampilkan video.

            @Query("part") String part,
            @Query("channelId") String channelId,
            @Query("key") String key,
            @Query("order") String order,                    //untuk menyortir hasil berdasarkan tanggal
            @Query("maxResults") String maxResults

    );






}
