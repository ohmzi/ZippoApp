package com.omar.zippoapp.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
        var isInvalidInput = true
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
        with(binding) {

            rvPostalDetails.layoutManager = LinearLayoutManager(this@ResultPage)
            rvPostalDetails.adapter = recyclerAdapter
            shimmerView.startShimmer()
            Handler(Looper.getMainLooper()).postDelayed({
                if (isInvalidInput == true) {
                    binding.errorImageView.visibility = View.VISIBLE
                }
            }, 6000)
        }
        intent.extras?.let {
            postalCodeInput = it.getString("postalCodeInput") as String
        }
        postalViewModel.makeAPICall(postalCodeInput)
        isInvalidInput = false


        postalViewModel.postalCodeList.observe(this) {
            val searchResults = it
            if (searchResults != null) {
                isInvalidInput = false
                recyclerAdapter.setAddressList(searchResults)
                recyclerAdapter.notifyDataSetChanged()
                with(binding) {
                    shimmerView.stopShimmer()
                    shimmerView.visibility = View.INVISIBLE
                    rvPostalDetails.visibility = View.VISIBLE

                }
            }
        }
        postalViewModel.errorLiveData.observe(this) {
            Toast.makeText(this, " API or Network Error, Please try again. ", Toast.LENGTH_LONG).show()
            with(binding) {
                shimmerView.stopShimmer()
                shimmerView.visibility = View.INVISIBLE
                errorImageView.visibility = View.VISIBLE
            }
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