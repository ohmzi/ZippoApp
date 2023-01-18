package com.omar.zippoapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omar.zippoapp.model.data.PostalCodeListData
import com.omar.zippoapp.model.repo.Repo
import kotlinx.coroutines.launch


class PostalViewModel : ViewModel() {
    private val repo by lazy { Repo() }
    private var _postalCodeList: MutableLiveData<PostalCodeListData?> = MutableLiveData()
    private val _postalCodeList2: MutableLiveData<PostalCodeListData?> = MutableLiveData()

    val postalCodeList: LiveData<PostalCodeListData?> = _postalCodeList
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData


    fun makeAPICall(postalCodeInput: String) {
        viewModelScope.launch {

            val postalCodeInputArray: Array<String> = postalCodeInput.split(",").toTypedArray()
            for (postalCodeInputIndividual in postalCodeInputArray) {

                val response =
                    repo.getPostalCodeList(postalCodeInputIndividual)

                if (response != null) {
                     _postalCodeList2.value = response
                } else {
                    _errorLiveData.value = "error occurred"
                }
            }
            _postalCodeList=_postalCodeList2
        }
    }
}
