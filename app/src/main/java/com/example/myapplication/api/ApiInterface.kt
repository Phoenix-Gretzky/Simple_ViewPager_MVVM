package com.example.myapplication.api

import com.example.myapplication.model.ApiData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    //    https://navkiraninfotech.com/g-mee-api/api/v1/apps/list?kid_id=378
    @POST("apps/list?kid_id=378")
    suspend fun getApplicationData(@Query("kid_id") kidId: String): Response<ApiData>


}