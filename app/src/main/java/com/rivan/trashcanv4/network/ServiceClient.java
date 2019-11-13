package com.rivan.trashcanv4.network;

import com.rivan.trashcanv4.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceClient {

    @FormUrlEncoded
    @POST("exec")
    Call<ResponseLogin> loginUser(
            @Field(value = "sheetName", encoded = true)String sheetName,
            @Field(value = "action", encoded = true)String login,
            @Field(value = "loginUser", encoded = true)String loginUser,
            @Field(value = "pass", encoded = true)String pass
    );
}
