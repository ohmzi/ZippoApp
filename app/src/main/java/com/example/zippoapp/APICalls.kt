package com.example.zippoapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APICalls {
    private val restaurants: MutableLiveData<PostalCodeListData?> = MutableLiveData()

   val TAG = "APICalls"
    fun postalCodeAPICall(postalCodeInput: String): MutableLiveData<PostalCodeListData?> {

        val retroInstance = RetroInstance.getRetroInstance()
        val yelpService = retroInstance.create(ZippopotamService::class.java)
        val call = yelpService.getRestaurantsResults(postalCodeInput)

        call.enqueue(
            object :
                Callback<PostalCodeListData> {
                override fun onResponse(
                    call: Call<PostalCodeListData>,
                    response: Response<PostalCodeListData>,
                ) {
                    Log.i("$TAG",
                        "makeAPICall onResponse on call $response")
                    val body = response.body()
                    restaurants.postValue(body)
                    Log.i("$TAG",
                        "Address: ${restaurants.value?.toString()}")
                }

                override fun onFailure(call: Call<PostalCodeListData>, t: Throwable) {
                    Log.i(TAG, "onFailure $t")
                }
            })
        return restaurants


    }

}
