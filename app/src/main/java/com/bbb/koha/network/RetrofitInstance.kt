package com.bbb.koha.network

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.bbb.koha.app.MyApplication
import com.bbb.koha.common.Constant
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private lateinit var retrofit: Retrofit

    private fun getRetrofitInstance(): Retrofit {
        val context: Context = MyApplication.getInstance().applicationContext
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            // development build
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            // production build
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        val authInterceptor = AuthInterceptor(context)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        if (!(::retrofit.isInitialized)) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit
    }

    val apiService: Apis? = getRetrofitInstance().create(Apis::class.java)

}