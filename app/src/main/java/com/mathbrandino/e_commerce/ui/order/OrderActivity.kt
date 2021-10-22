package com.mathbrandino.e_commerce.ui.order

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mathbrandino.e_commerce.databinding.ActivityOrderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : AppCompatActivity() {

    private val binding: ActivityOrderBinding by lazy {
        ActivityOrderBinding.inflate(layoutInflater)
    }
    private val viewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.orders.observe(this) {
            binding.root.adapter = OrderAdapter(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}