package com.example.zippoapp.model.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.zippoapp.model.data.PostalCodeListData
import com.example.zippoapp.model.retroServices.RetroInstance
import com.example.zippoapp.model.retroServices.ZippopotamService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APICalls {
    private val postalCodeList: MutableLiveData<PostalCodeListData?> = MutableLiveData()
    private val TAG = "APICalls"
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
                    val body = response.body()
                    postalCodeList.postValue(body)
                    Log.i(TAG,
                        "Address: ${postalCodeList.value?.toString()}")
                }

                override fun onFailure(call: Call<PostalCodeListData>, t: Throwable) {
                    Log.i(TAG, "onFailure $t")
                }
            })
        return postalCodeList
    }

}
