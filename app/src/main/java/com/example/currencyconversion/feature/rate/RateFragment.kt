package com.example.currencyconversion.feature.rate

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconversion.NOTIFICATION_CHANNEL_ID
import com.example.currencyconversion.R
import com.example.currencyconversion.databinding.FragmentRateBinding
import com.example.currencyconversion.service.ReloadService
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RateFragment : Fragment() {
    private val viewModel: RateViewModel by viewModel()
    private val sharedViewModel: SharedViewModel by sharedViewModel()
    private lateinit var binding: FragmentRateBinding
    private lateinit var controller: RateController
    private val sharedViewPool = RecyclerView.RecycledViewPool()
    private lateinit var jobInfo: JobInfo
    private val jobId = 123

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rate, container, false)
        controller = RateController()
        viewModel.rates.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            controller.setData(it)

            // currencyの変更があってから30分間隔で更新にしたい
            // amountでの変更のときは無視したいが...
            val scheduler =
                requireActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.schedule(jobInfo)
        })
        sharedViewModel.currency.observe(viewLifecycleOwner, Observer {
            viewModel.reGetRate(it)
        })
        sharedViewModel.amount.observe(viewLifecycleOwner, Observer {
            viewModel.reCalc(it)
        })
        binding.rvRates.apply {
            clipChildren = false
            setHasFixedSize(true)
            setRecycledViewPool(sharedViewPool)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = controller.adapter
            val dividerItemDecoration = DividerItemDecoration(
                context,
                LinearLayoutManager(requireActivity()).orientation
            )
            addItemDecoration(dividerItemDecoration)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.init()
        createJobScheduler()
    }

    private fun createJobScheduler() {
        registerNotificationChannel()
        val componentName = ComponentName(requireContext(), ReloadService::class.java)
        jobInfo = JobInfo.Builder(jobId, componentName)
            .apply {
                setBackoffCriteria(5000, JobInfo.BACKOFF_POLICY_LINEAR)
                setPersisted(false)
                setPeriodic(30 * 60 * 1000)
                setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                setRequiresCharging(false)
            }.build()
    }

    private fun registerNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "notification"
            val descriptionText = "notification channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            val notificationManager =
                requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}
