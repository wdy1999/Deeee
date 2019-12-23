package com.wd.kotlin

import com.wd.kotlin.bean.LoginBean
import com.wd.kotlin.bean.RegisterBean
import io.reactivex.Observable
import retrofit2.http.*

interface Api {
    //注册
    @FormUrlEncoded
    @POST("techApi/user/v1/register")
    fun register(@FieldMap map: Map<String, String>): Observable<RegisterBean>

    //登录
    @FormUrlEncoded
    @POST("techApi/user/v1/login")
    fun login(@Field("phone") phone: String, @Field("pwd") pwd: String):Observable<LoginBean>


}