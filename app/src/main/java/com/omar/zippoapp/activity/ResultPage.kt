package com.omar.zippoapp.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.omar.zippoapp.adapter.AddressResultsAdapter
import com.omar.zippoapp.databinding.ResultsPageBinding
import com.omar.zippoapp.viewModel.PostalViewModel

class ResultPage : AppCompatActivity() {


    private lateinit var binding: ResultsPageBinding
    private val recyclerAdapter by lazy { AddressResultsAdapter(this) }
    private lateinit var postalCodeInput: String
    val postalViewModel: PostalViewModel =
        ViewModelProvider(this)[PostalViewModel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ResultsPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(this@ResultPage)
        binding.recyclerView.adapter = recyclerAdapter



        intent.extras?.let {
           postalCodeInput = it.getString("postalCodeInput") as String
        }

        postalViewModel.makeAPICall(postalCodeInput)

        postalViewModel.postalCodeList.observe(this) {
            val searchResults = it

            if (searchResults != null && searchResults.country != "") {
                Log.d("testing searchResults ", searchResults.country.toString())
                recyclerAdapter.setAddressList(listOf(searchResults))
                recyclerAdapter.setAddressFullDetailList(searchResults.places)
                recyclerAdapter.notifyDataSetChanged() //itemrangechange
                binding.recyclerView.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }

        }
        postalViewModel.errorLiveData.observe(this) {
            Toast.makeText(this, " API Error ", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}