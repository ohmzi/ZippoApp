package com.example.zippoapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zippoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var postalCodeInput: String = "156584"
    private val recyclerAdapter by lazy { AddressResultsAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.rvRestaurants.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvRestaurants.adapter = recyclerAdapter
        val searchButton = binding.bnSearch
        val postalViewModel: PostalViewModel =
            ViewModelProvider(this)[PostalViewModel::class.java]

        searchButton.setOnClickListener {
            with(binding) {
                postalCodeInput = postalCodeTextInput.text.toString()
            }
            postalViewModel.makeAPICall(postalCodeInput)

            postalViewModel.getPostalCodeListDetails().observe(this) {
                Log.d("APICalls", "Country Abbreviation:  $it")
                val searchResults = it
                if (searchResults != null) {
                    recyclerAdapter.setAddressList(listOf(searchResults))
                    recyclerAdapter.setAddressFullDetailList(searchResults.places)
                    recyclerAdapter.notifyDataSetChanged()
                    binding.rvRestaurants.visibility = View.VISIBLE

                    Log.d("APICalls1",
                        "Country Abbreviation:  ${searchResults.countryAbbreviation}")
                }
            }
        }


    }
}