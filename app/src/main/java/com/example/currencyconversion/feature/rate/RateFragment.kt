package com.example.currencyconversion.feature.rate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconversion.R
import com.example.currencyconversion.databinding.FragmentRateBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RateFragment : Fragment() {
    private val viewModel: RateViewModel by viewModel()
    private lateinit var binding: FragmentRateBinding
    private lateinit var controller: RateController
    private val sharedViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rate, container, false)
        controller = RateController()
        viewModel.rates.observe(viewLifecycleOwner, Observer {
            controller.setData(it)
        })
        binding.rvRates.apply {
            clipChildren = false
            setHasFixedSize(true)
            setRecycledViewPool(sharedViewPool)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = controller.adapter
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.init()
    }
}
