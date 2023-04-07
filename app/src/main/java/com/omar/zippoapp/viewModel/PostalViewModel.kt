package com.omar.zippoapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omar.zippoapp.model.data.PostalCodeListData
import com.omar.zippoapp.model.repo.Repo
import kotlinx.coroutines.launch


class PostalViewModel : ViewModel() {
    private val repo by lazy { Repo() }
    private var _postalCodeList: MutableLiveData<List<PostalCodeListData>> = MutableLiveData()
    private val _postalCodeList2: MutableLiveData<PostalCodeListData?> = MutableLiveData()

    val postalCodeList: LiveData<List<PostalCodeListData>> = _postalCodeList
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData


    fun makeAPICall(postalCodeInput: String) {
        viewModelScope.launch {

            val postalCodeResultList = arrayListOf<PostalCodeListData>()
            val postalCodeInputArray: Array<String> = postalCodeInput.split(",").toTypedArray()

            for (postalCodeInputIndividual in postalCodeInputArray) {

                val response =
                    repo.getPostalCodeList(postalCodeInputIndividual)

                response?.let {
                    postalCodeResultList.add(it)
                }
            }

            if (postalCodeResultList.isNotEmpty()) {
                _postalCodeList.value = postalCodeResultList
            } else {
                _errorLiveData.value = "error occurred"
            }
        }
       // _postalCodeList.value=_postalCodeList2.value

    }
}
