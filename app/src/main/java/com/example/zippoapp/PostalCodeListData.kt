package com.example.zippoapp

import com.google.gson.annotations.SerializedName

data class PostalCodeListData(
    @SerializedName("post code")
    val postalCodeNum: String,
    val country: String,
    @SerializedName("country abbreviation")
    val countryAbbreviation: String,
    val places: List<FullAddressDetails>,
) {

    data class FullAddressDetails(
        @SerializedName("place name") val city: String,
        val longitude: String,
        val state: String,
        @SerializedName("state abbreviation") val stateAbbreviation: String,
        val latitude: String,
    )

}
