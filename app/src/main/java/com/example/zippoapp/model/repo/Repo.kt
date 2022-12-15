package com.example.zippoapp.model.repo

import androidx.lifecycle.MutableLiveData
import com.example.zippoapp.model.data.PostalCodeListData

class Repo {
    private val apiCall = APICalls()

    suspend fun getPostalCodeList(
        postalCodeInput: String,
    ): PostalCodeListData? {
        return try {
            apiCall.postalCodeAPICall(
                postalCodeInput
            )
        } catch (e: Exception) {
            null
        }

    }

}
