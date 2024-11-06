package com.huyngo.projecthuyandroid.myconverter.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.huyngo.projecthuyandroid.R
import com.huyngo.projecthuyandroid.databinding.ActivityHomeBinding
import com.huyngo.projecthuyandroid.myconverter.data.Resource
import com.huyngo.projecthuyandroid.myconverter.errors.ErrorManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var errorManager: ErrorManager

    private lateinit var binding: ActivityHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()

        setupConvertButton()


        observeData()

    }

    private fun observeData() {
        homeViewModel.exchangeRateLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Success -> {
                    hideLoading()
                    val exchangeRates = resource.data?.exchangeRateData?.data

                    val baseAmount = binding.etAmount.text.toString().toDoubleOrNull()
                    val fromCurrency = binding.spinnerFromCurrency.selectedItem.toString()
                    val toCurrency = binding.spinnerToCurrency.selectedItem.toString()

                    val exchangeRateOfSelectedCurrency = exchangeRates?.get(toCurrency) ?: 0.0
                    val convertedCurrencyValue = baseAmount?.times(exchangeRateOfSelectedCurrency)

                    binding.tvResult.text = String.format("%.2f", convertedCurrencyValue)
                    binding.tvExchangeRateValue.text =
                        String.format("%.2f", exchangeRateOfSelectedCurrency)
                }

                is Resource.Error -> {
                    hideLoading()
                    resource.errorCode?.let {
                        val error = errorManager.getError(it)
                        Toast.makeText(this, error.description, Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
                    showLoading()
                }
            }

        }
    }

    private fun setupConvertButton() {
        binding.btnConvert.setOnClickListener {
            val amount = binding.etAmount.text.toString().toDoubleOrNull()
            val fromCurrency = binding.spinnerFromCurrency.selectedItem.toString()
            val toCurrency = binding.spinnerToCurrency.selectedItem.toString()

            if (amount != null) {
                homeViewModel.getExchangeRate(fromCurrency, toCurrency)
            } else {
                binding.tvResult.text = "0"
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.currency_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerFromCurrency.adapter = adapter
            binding.spinnerToCurrency.adapter = adapter
        }
    }

    private fun hideLoading() {
        binding.loadingLayout.visibility = View.GONE
    }

    private fun showLoading() {
        binding.loadingLayout.visibility = View.VISIBLE
    }
}