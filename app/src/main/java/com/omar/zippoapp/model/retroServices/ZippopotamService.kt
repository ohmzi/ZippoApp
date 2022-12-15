package com.omar.zippoapp.model.retroServices

import com.omar.zippoapp.model.data.PostalCodeListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ZippopotamService {
    @GET("us/{postal-code}")
    fun getRestaurantsResults(@Path("postal-code") postalCodeInput: String): Call<PostalCodeListData>


}
