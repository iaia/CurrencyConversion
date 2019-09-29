package com.example.currencyconversion.feature.rate

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.currencyconversion.R
import com.example.currencyconversion.databinding.FragmentRateBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RateFragment : Fragment() {
    private val viewModel: RateViewModel by viewModel()
    private lateinit var binding: FragmentRateBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rate, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.init()
    }
}
