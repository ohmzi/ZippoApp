package com.omar.zippoapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.omar.zippoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postalCodeInput: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val searchButton = binding.bnSearch
        val intent = Intent(this, ResultPage::class.java)
        val extras = Bundle()

        searchButton.setOnClickListener {
            with(binding) {
                postalCodeInput = postalCodeTextInput.text.toString()
            }
            if (postalCodeInput.isEmpty()) {
                Toast.makeText(this, "Please Enter Postal Code", Toast.LENGTH_SHORT).show()

            } else {
                extras.putString("postalCodeInput", postalCodeInput)
                intent.putExtras(extras)
                startActivity(intent)
            }

        }


    }
}