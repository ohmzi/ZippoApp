package com.example.zippoapp.model.repo

import androidx.lifecycle.MutableLiveData
import com.example.zippoapp.model.data.PostalCodeListData

class Repo {
    private val apiCall = APICalls()
    private var postalCodeList: MutableLiveData<PostalCodeListData?> = MutableLiveData()

    fun getPostalCodeList(postalCodeInput: String
    ): MutableLiveData<PostalCodeListData?> {
        postalCodeList =
            apiCall.postalCodeAPICall(postalCodeInput)
        return postalCodeList
    }

}
