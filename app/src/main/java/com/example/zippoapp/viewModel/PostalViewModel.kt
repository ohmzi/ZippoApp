package com.example.zippoapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zippoapp.model.data.PostalCodeListData
import com.example.zippoapp.model.repo.Repo
import kotlinx.coroutines.launch


class PostalViewModel : ViewModel() {
    private val repo by lazy { Repo() }
    private val _postalCodeList: MutableLiveData<PostalCodeListData?> = MutableLiveData()
    val postalCodeList: LiveData<PostalCodeListData?> = _postalCodeList
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData


    fun makeAPICall(postalCodeInput: String) {
        viewModelScope.launch {

            val res: Array<String> = postalCodeInput.split(",").toTypedArray()
            for (myStr in res) {

                val response =
                    repo.getPostalCodeList(myStr)

                if (response != null) {
                    _postalCodeList.value = response
                } else {
                    _errorLiveData.value = "error occurred"
                }
            }
        }
    }




}
