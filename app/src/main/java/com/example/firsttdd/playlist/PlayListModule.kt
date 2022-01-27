package com.example.firsttdd.playlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(FragmentComponent::class)
class PlayListModule {

    @Provides
    fun playListApi(retrofit: Retrofit): PlayListApi {
      return  retrofit.create(PlayListApi::class.java)
    }

    @Provides
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://172.22.224.1:3000/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}