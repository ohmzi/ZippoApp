package com.example.zippoapp.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zippoapp.adapter.AddressResultsAdapter
import com.example.zippoapp.databinding.ResultsPageBinding
import com.example.zippoapp.viewModel.PostalViewModel

class ResultPage : AppCompatActivity() {


    private lateinit var binding: ResultsPageBinding
    private val recyclerAdapter by lazy { AddressResultsAdapter(this) }
    private var postalCodeInput: String = "80036"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ResultsPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.rvRestaurants.layoutManager = LinearLayoutManager(this@ResultPage)
        binding.rvRestaurants.adapter = recyclerAdapter
        val postalViewModel: PostalViewModel =
            ViewModelProvider(this)[PostalViewModel::class.java]
        intent.extras.let {
            val bundle = it
            if (bundle != null) {
                postalCodeInput = bundle.getString("postalCodeInput") as String
            }
        }


        postalViewModel.makeAPICall(postalCodeInput)

        postalViewModel.postalCodeList.observe(this) {
            val searchResults = it
            if (searchResults != null) {
                recyclerAdapter.setAddressList(listOf(searchResults))
                recyclerAdapter.setAddressFullDetailList(searchResults.places)
                recyclerAdapter.notifyDataSetChanged()
                binding.rvRestaurants.visibility = View.VISIBLE
            } else
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}