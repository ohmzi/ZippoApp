package com.omar.zippoapp.activity

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.omar.zippoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postalCodeInput: String
    private lateinit var view:  ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        val searchButton = binding.bnSearch
        val intent = Intent(this, ResultPage::class.java)
        val extras = Bundle()
        var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        var editor = sharedPreferences.edit()

        searchButton.setOnClickListener {
            with(binding) {
                postalCodeInput = postalCodeTextInput.text.toString().replace(" ", "")
            }
            if (postalCodeInput.isEmpty()) {
                Toast.makeText(this, "Please Enter Postal Code", Toast.LENGTH_SHORT).show()

            } else {
                editor.putString("key", postalCodeInput)
                editor.apply()
                extras.putString("postalCodeInput", postalCodeInput)
                intent.putExtras(extras)
                startActivity(intent)
            }
        }
        binding.bnHistory.setOnClickListener {

            binding.postalCodeTextInput.setText(sharedPreferences.getString("key", ""))

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