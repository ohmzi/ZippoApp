package com.omar.zippoapp.model.repo

import android.util.Log
import com.omar.zippoapp.model.data.PostalCodeListData
import com.omar.zippoapp.model.retroServices.RetroInstance
import com.omar.zippoapp.model.retroServices.ZippopotamService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class APICalls {
    private val TAG = "APICalls"
    suspend fun postalCodeAPICall(postalCodeInput: String) = suspendCoroutine<PostalCodeListData> {

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

                    if (response.body() != null) {
                        val body = response.body()
                        it.resume(body as PostalCodeListData)
                        Log.i(TAG,
                            "Address: ${response.body()}")
                    } else
                        Log.i(TAG, "response = Null")
                }

                override fun onFailure(call: Call<PostalCodeListData>, t: Throwable) {
                    Log.i(TAG, "onFailure $t")
                    it.resumeWithException(t)
                }
            })
    }

}
