package com.example.currencyconversion.feature.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.currencyconversion.R
import com.example.currencyconversion.databinding.FragmentCurrencyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyFragment : Fragment() {
    private val viewModel: CurrencyViewModel by viewModel()
    private lateinit var binding: FragmentCurrencyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_currency, container, false)
        viewModel.currencies.observe(viewLifecycleOwner, Observer {
            //val adapter = SimpleCursorAdapter(context, android.R.layout.simple_spinner_item, listOf("code", "name"), binding.sCurrencies.id)
            val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, it)
            binding.sCurrencies.adapter = adapter
        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.init()
    }
}
