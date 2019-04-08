package com.ayat.candyapp.network;

import com.blessedtreeit.product.activitycenter.flows.login.model.LoginModel;
import com.blessedtreeit.product.activitycenter.flows.login.model.LoginRequestModel;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Ayat khraisat  on 12/27/18
 * akhraisat@blessedtreeit.com
 * <p>
 * Project Name: ActivityCenter
 * <p>
 * Blessed Tree IT
 */
public interface EndPoint {

    @POST("api/Account/Login")
    Single<LoginModel> login(@NonNull @Body LoginRequestModel loginRequestModel);

}
