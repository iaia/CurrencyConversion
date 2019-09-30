package com.example.currencyconversion.feature.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.currencyconversion.R
import com.example.currencyconversion.databinding.FragmentCurrencyBinding
import com.example.currencyconversion.feature.rate.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyFragment : Fragment() {
    private val viewModel: CurrencyViewModel by viewModel()
    private val sharedViewModel: SharedViewModel by sharedViewModel()
    private lateinit var binding: FragmentCurrencyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_currency, container, false)
        viewModel.currencies.observe(viewLifecycleOwner, Observer {
            //val adapter = SimpleCursorAdapter(context, android.R.layout.simple_spinner_item, listOf("code", "name"), binding.sCurrencies.id)
            val adapter = ArrayAdapter(context, R.layout.view_simple_spinner_item, it)
            binding.sCurrencies.adapter = adapter
        })
        viewModel.selectedCurrency.observe(viewLifecycleOwner, Observer {
            sharedViewModel.currency.postValue(it)
        })
        binding.sCurrencies.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                viewModel.selectCurrency(position)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.init()
    }
}
