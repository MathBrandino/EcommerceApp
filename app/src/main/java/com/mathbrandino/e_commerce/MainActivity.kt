package com.mathbrandino.e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mathbrandino.e_commerce.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}