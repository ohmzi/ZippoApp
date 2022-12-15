package com.omar.zippoapp.model.repo

import com.omar.zippoapp.model.data.PostalCodeListData

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
