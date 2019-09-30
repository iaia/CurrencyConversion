package com.example.currencyconversion.feature.amount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.currencyconversion.R
import com.example.currencyconversion.databinding.FragmentAmountBinding
import com.example.currencyconversion.feature.rate.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AmountFragment : Fragment() {
    private val viewModel: AmountViewModel by viewModel()
    private val sharedViewModel: SharedViewModel by sharedViewModel()
    private lateinit var binding: FragmentAmountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_amount, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.amount.observe(viewLifecycleOwner, Observer {
            sharedViewModel.amount.postValue(it)
        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
    }
}
