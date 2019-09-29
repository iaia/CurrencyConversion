package com.example.currencyconversion.feature.amount

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil

import com.example.currencyconversion.R
import com.example.currencyconversion.databinding.FragmentAmountBinding
import com.example.currencyconversion.databinding.FragmentCurrencyBinding
import com.example.currencyconversion.feature.currency.CurrencyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AmountFragment : Fragment() {
    private val viewModel: AmountViewModel by viewModel()
    private lateinit var binding: FragmentAmountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_amount, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
    }
}
