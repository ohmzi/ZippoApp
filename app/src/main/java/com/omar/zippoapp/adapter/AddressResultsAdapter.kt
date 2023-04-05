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

    fun setAddressList(searchResults: List<PostalCodeListData>?) {
        this.postalCodeList = searchResults

    }

    inner class ViewHolder(private val binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindPostalBasicDetail(addressList: PostalCodeListData) {
            with(binding) {
                tvPostalCodeNum.text = "Postal Code: " + addressList.postalCodeNum
                tvCountry.text = "Country" + addressList.country
                tvCountryAbb.text = "Country Abbreviation: " + addressList.countryAbbreviation
            }
        }

        @SuppressLint("SetTextI18n")
        fun bindPostalFullDetail(addressList: PostalCodeListData.FullAddressDetails) {
            with(binding) {
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
        postalCodeList?.get(position)?.let {
            holder.bindPostalBasicDetail((it))
            holder.bindPostalFullDetail((it.places.first()))
        }
    }

    override fun getItemCount(): Int {
        return postalCodeList?.size ?: 0
    }
}