package com.omar.zippoapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omar.zippoapp.activity.ResultPage
import com.omar.zippoapp.databinding.ItemAddressBinding
import com.omar.zippoapp.model.data.PostalCodeListData

class AddressResultsAdapter(private val mainActivity: ResultPage) :
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
        fun bindPostalBasicDetail(addressList: PostalCodeListData, mainActivity: ResultPage) {
            with(binding)
            {
                tvPostalCodeNum.text = "Postal Code: " + addressList.postalCodeNum
                tvCountry.text = "Country" + addressList.country
                tvCountryAbb.text = "Country Abbreviation: " + addressList.countryAbbreviation
            }
        }

        @SuppressLint("SetTextI18n")
        fun bindPostalFullDetail(
            addressList: PostalCodeListData.FullAddressDetails,
            mainActivity: ResultPage,
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
    ): ViewHolder {
        val binding = ItemAddressBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        postalCodeList?.get(position)?.let { holder.bindPostalBasicDetail((it), mainActivity) }
        fullAddressDetailList?.get(position)
            ?.let { holder.bindPostalFullDetail((it), mainActivity) }
    }

    override fun getItemCount(): Int {
        return postalCodeList?.size ?: 0
    }
}