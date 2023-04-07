package com.omar.zippoapp.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.omar.zippoapp.adapter.AddressResultsAdapter
import com.omar.zippoapp.databinding.ResultsPageBinding
import com.omar.zippoapp.viewModel.PostalViewModel

class ResultPage : AppCompatActivity() {
    companion object {
        var isInvalidInput = false
    }

    private lateinit var binding: ResultsPageBinding
    private val recyclerAdapter by lazy { AddressResultsAdapter(this) }
    private lateinit var postalCodeInput: String
    private lateinit var view: ConstraintLayout
    private val postalViewModel: PostalViewModel by lazy {
        ViewModelProvider(this)[PostalViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ResultsPageBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)

        binding.rvPostalDetails.layoutManager = LinearLayoutManager(this@ResultPage)
        binding.rvPostalDetails.adapter = recyclerAdapter

        intent.extras?.let {
            postalCodeInput = it.getString("postalCodeInput") as String
        }
        isInvalidInput=false
        postalViewModel.makeAPICall(postalCodeInput)

        if(isInvalidInput){
            Toast.makeText(this, "Invalid Input ", Toast.LENGTH_LONG).show()

        }
        postalViewModel.postalCodeList.observe(this) {
            val searchResults = it
            recyclerAdapter.setAddressList(searchResults)
            recyclerAdapter.notifyDataSetChanged()
            binding.errorImageView.visibility =View.INVISIBLE
            binding.rvPostalDetails.visibility = View.VISIBLE
        }
        postalViewModel.errorLiveData.observe(this) {
            Toast.makeText(this, " API Error ", Toast.LENGTH_LONG).show()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("onSaveInstanceState", postalCodeInput)
        outState.putString("postalCodeInput", postalCodeInput)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle,
    ) {
        super.onRestoreInstanceState(savedInstanceState)
        postalCodeInput = savedInstanceState.getString("postalCodeInput", postalCodeInput)
        Log.d("onRestoreInstanceState", postalCodeInput)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}