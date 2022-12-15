package com.example.zippoapp

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zippoapp.databinding.ItemAddressBinding

class AddressResultsAdapter(private val mainActivity: MainActivity) :
    RecyclerView.Adapter<AddressResultsAdapter.ViewHolder>() {

    private var postalCodeList: List<PostalCodeListData>? = null
    private var fullAddressDetailList: List<PostalCodeListData.FullAddressDetails>? = null

    fun setAddressList(searchResults: List<PostalCodeListData>?) {
        this.postalCodeList = searchResults

    }
    fun setAddressFullDetailList(searchResultsFullDetailList: List<PostalCodeListData.FullAddressDetails>?) {
        this.fullAddressDetailList = searchResultsFullDetailList

    }

    inner class ViewHolder(private val binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindPostalBasicDetail(addressList: PostalCodeListData, mainActivity: MainActivity) {
            with(binding)
            {
                Log.d("bindPostalBasicDetail: postalCodeNum",addressList.postalCodeNum )
                tvPostalCodeNum.text = "Postal Code: " + addressList.postalCodeNum
                tvCountry.text = "Country" + addressList.country
                tvCountryAbb.text = "Country Abbreviation: " + addressList.countryAbbreviation
            }
        }

        @SuppressLint("SetTextI18n")
        fun bindPostalFullDetail(
            addressList: PostalCodeListData.FullAddressDetails,
            mainActivity: MainActivity,
        ) {
            with(binding)
            {
                tvCity.text = "City: " + addressList.city
                tvState.text = "State Abbreviation: " + addressList.state
                tvStateAbb.text = "State Abbreviation: " + addressList.stateAbbreviation
                tvLongitude.text = "Longitude: " + addressList.longitude
                tvLatitude.text = "Latitude: " + addressList.latitude
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AddressResultsAdapter.ViewHolder {
        val binding = ItemAddressBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        postalCodeList?.get(position)?.let { holder.bindPostalBasicDetail((it), mainActivity) }
        fullAddressDetailList?.get(position)?.let { holder.bindPostalFullDetail((it), mainActivity) }
    }

    override fun getItemCount(): Int {
        return postalCodeList?.size ?: 0
    }
}