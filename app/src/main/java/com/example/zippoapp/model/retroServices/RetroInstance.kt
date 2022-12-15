package com.example.zippoapp.model.retroServices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {
        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.zippopotam.us/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
