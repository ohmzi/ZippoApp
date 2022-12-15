package com.example.zippoapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PostalViewModel : ViewModel() {
    private val repo by lazy { Repo() }

    private var postalCodeList: MutableLiveData<PostalCodeListData?> = MutableLiveData()


    fun getPostalCodeListDetails(): MutableLiveData<PostalCodeListData?> {
        return postalCodeList
    }

    fun takingInput(postalCodeInput: String){
        val res: Array<String> = postalCodeInput.split(",").toTypedArray()
        for (myStr in res) {
            Log.w("PostalViewModel takingInput ", myStr)

        }
    }

    fun makeAPICall(postalCodeInput: String) {
        val res: Array<String> = postalCodeInput.split(",").toTypedArray()
        for (myStr in res) {
            Log.w("PostalViewModel takingInput ", myStr)
            postalCodeList =
                repo.getPostalCodeList(postalCodeInput)

        }
        Log.w("PostalViewModel makeAPICall", repo.toString())
    }




}
