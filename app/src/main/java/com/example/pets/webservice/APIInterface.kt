package com.example.pets.webservice

import com.example.pets.constants.Constants
import com.example.pets.model.Content
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor



interface APIInterface {
    @GET("people.json")
    fun getContents(): Observable<List<Content>>

    companion object {
        fun create(): APIInterface {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
            return retrofit.create(APIInterface::class.java);
        }
    }
}