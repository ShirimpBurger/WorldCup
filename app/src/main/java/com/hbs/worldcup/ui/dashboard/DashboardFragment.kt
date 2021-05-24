package com.hbs.worldcup.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.hbs.worldcup.R
import com.hbs.worldcup.core.BaseFragment
import com.hbs.worldcup.databinding.DashboardFragmentBinding
import com.hbs.worldcup.models.FragmentInitializer
import com.hbs.worldcup.ui.quiz.QuizActivity
import com.hbs.worldcup.ui.dashboard.alarm.AlarmDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<DashboardFragmentBinding>() {
    private val viewModel by viewModels<DashboardViewModel>()

    private val dashboardAdapter = DashboardListAdapter { card, _ ->
        startActivity(Intent(context, QuizActivity::class.java))
    }.apply {
        setHasStableIds(true)
    }

    override fun getFragmentInitializer(): FragmentInitializer =
        FragmentInitializer(R.layout.dashboard_fragment)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindAdapter()
        bindViewModel()
        bindView()
        observeViewModel()
    }

    private fun bindViewModel() {
        binding.viewModel = viewModel
    }

    private fun bindAdapter(){
        binding.dashboardRecyclerView.adapter = dashboardAdapter
    }

    private fun bindView() {
        binding.callback = Callback {
            AlarmDialog().show(parentFragmentManager, "ALARM_DIALOG")
        }
    }

    private fun observeViewModel() {
        viewModel.largeCardList.observe(viewLifecycleOwner, {
            dashboardAdapter.submitList(it)
        })
    }

    fun interface Callback {
        fun onClick()
    }
}