package com.jcy.moviereviewapp.modules

import com.jcy.moviereviewapp.BuildConfig
import com.jcy.moviereviewapp.api.ApiInterface
import com.jcy.moviereviewapp.api.Url
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val apiModule: Module = module{

    single<ApiInterface>{ get<Retrofit>().create(ApiInterface::class.java)}

    single<Retrofit>{
        Retrofit.Builder()
            .baseUrl(Url.NAVER_SEARCH_MOVIE_BASE_URL)
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }
    single<GsonConverterFactory> { GsonConverterFactory.create() }

    single<OkHttpClient>{
        OkHttpClient.Builder()
            .run{
                addInterceptor(get<Interceptor>())
                build()
            }
    }
    single<Interceptor>{
        Interceptor{
            with(it){
                val newRequest = request().newBuilder()
                    .addHeader("X-Naver-Client-Id",BuildConfig.NAVER_CLIENT_ID)
                    .addHeader("X-Naver-Client-Secret",BuildConfig.NAVER_CLIENT_SECRET)
                    .build()
                proceed(newRequest)
            }
        }
    }
}