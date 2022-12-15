package com.example.zippoapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zippoapp.model.data.PostalCodeListData
import com.example.zippoapp.model.repo.Repo

class PostalViewModel : ViewModel() {
    private val repo by lazy { Repo() }

    private var postalCodeList: MutableLiveData<PostalCodeListData?> = MutableLiveData()


    fun getPostalCodeListDetails(): MutableLiveData<PostalCodeListData?> {
        return postalCodeList
    }



    fun makeAPICall(postalCodeInput: String) {

        val res: Array<String> = postalCodeInput.split(",").toTypedArray()
        for (myStr in res) {
            postalCodeList =
                repo.getPostalCodeList(myStr)
        }
    }




}
