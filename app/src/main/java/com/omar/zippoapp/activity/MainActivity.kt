package com.omar.zippoapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.omar.zippoapp.databinding.ActivityMainBinding
import com.omar.zippoapp.viewModel.PostalViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postalCodeInput: String
    private lateinit var view: ConstraintLayout
    private val postalViewModel: PostalViewModel by lazy {
        ViewModelProvider(this)[PostalViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)

    }

    override fun onStart() {
        super.onStart()
        val searchButton = binding.bnSearch
        val historyButton = binding.bnHistory
        val intent = Intent(this, ResultPage::class.java)
        val extras = Bundle()

        searchButton.setOnClickListener {

            with(binding) {
                postalCodeInput = postalCodeTextInput.text.toString().replace(" ", "")
            }
            postalViewModel.setHistory(postalCodeInput)

            if (postalCodeInput.isEmpty()) {
                Toast.makeText(this, "Please Enter Postal Code", Toast.LENGTH_SHORT).show()

            } else {
                extras.putString("postalCodeInput", postalCodeInput)
                intent.putExtras(extras)
                startActivity(intent)
            }
        }

        historyButton.setOnClickListener {

            with(binding) {
                postalCodeTextInput.setText(postalViewModel.getHistory.toString())
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

}