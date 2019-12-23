package com.wd.kotlin.tool

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtlis private constructor() {
    var retrofit: Retrofit? = null
    companion object {
        val instance: RetrofitUtlis by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitUtlis()
        }
    }


    //构造方法
    init {
        //拦截器
        var httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        //okHttpClient
        var okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
        //Retrofit
             retrofit = Retrofit.Builder()
            .baseUrl("https://mobile.bwstudent.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun <T> create(tClass: Class<T>): T {
        return retrofit!!.create(tClass)
    }


}