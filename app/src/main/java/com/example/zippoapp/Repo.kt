package com.example.zippoapp

import android.util.Log
import androidx.lifecycle.MutableLiveData

class Repo {
    private val apiCall = APICalls()
    private var postalCodeList: MutableLiveData<PostalCodeListData?> = MutableLiveData()

    fun getPostalCodeList(postalCodeInput: String
    ): MutableLiveData<PostalCodeListData?> {
        postalCodeList =
            apiCall.postalCodeAPICall(postalCodeInput)
        Log.d("Repo", "Country Abbreviation:  ${postalCodeList.value?.countryAbbreviation.toString()}" )

        return postalCodeList
    }

}
