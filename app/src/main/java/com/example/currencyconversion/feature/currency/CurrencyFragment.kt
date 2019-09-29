package com.example.currencyconversion.feature.currency

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.currencyconversion.R
import com.example.currencyconversion.databinding.FragmentCurrencyBinding
import com.example.currencyconversion.databinding.FragmentRateBinding
import com.example.currencyconversion.feature.rate.RateController
import com.example.currencyconversion.feature.rate.RateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyFragment : Fragment() {
    private val viewModel: CurrencyViewModel by viewModel()
    private lateinit var binding: FragmentCurrencyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rate, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.init()
    }
}
