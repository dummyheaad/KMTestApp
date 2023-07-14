package com.example.kmtestapp.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.kmtestapp.R
import com.example.kmtestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupActionCheck()
        setupActionNext()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupActionCheck() {
        binding.btnCheck.setOnClickListener {
            val text: String = binding.edPalindrome.text.toString()
            if (text == "") {
                binding.edPalindrome.error = getString(R.string.error_palindrome)
            }
            else {
                if (isPalindrome(text)) {
                    Toast.makeText(this, getString(R.string.is_palindrome), Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, getString(R.string.is_not_palindrome), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isPalindrome(text: String): Boolean =
        text.lowercase().replace(" ", "") == text.lowercase().replace(" ", "").reversed()

    private fun setupActionNext() {
        binding.btnNext.setOnClickListener {
            val name = binding.edName.text.toString()
            if (name == "") {
                binding.edName.error = getString(R.string.error_name)
            }
            else {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra(NAME, name)
                startActivity(intent)
            }
        }
    }

    companion object {
        const val NAME = "name"
    }
}