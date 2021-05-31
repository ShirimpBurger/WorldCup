package com.hbs.worldcup.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.hbs.worldcup.R
import com.hbs.worldcup.core.BaseFragment
import com.hbs.worldcup.databinding.DashboardFragmentBinding
import com.hbs.worldcup.models.FragmentInitializer
import com.hbs.worldcup.ui.dashboard.DashboardFragment.Callback
import com.hbs.worldcup.ui.dashboard.alarm.AlarmDialog
import com.hbs.worldcup.ui.quiz.QuizActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<DashboardFragmentBinding>() {
    private val viewModel : DashboardViewModel by viewModels()
    private val listAdapter by lazy { DashboardListAdapter(clickCallback) }

    override fun getFragmentInitializer() = FragmentInitializer(R.layout.dashboard_fragment)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        bindView()
        observeViewModel()
    }

    private val clickCallback =
        DashboardListAdapter.Callback { card, _ ->
            startActivity(Intent(context, QuizActivity::class.java))
        }

    private fun bindViewModel() {
        binding.viewModel = viewModel
    }

    private fun bindView() {
        with(binding.dashboardRecyclerView) {
            adapter = listAdapter
        }

        binding.callback = Callback {
            AlarmDialog().show(parentFragmentManager, "ALARM_DIALOG")
        }
    }

    private fun observeViewModel() {
        viewModel.largeCardList.observe(viewLifecycleOwner, {
            listAdapter.submitList(it)
        })
    }

    fun interface Callback {
        fun onClick()
    }
}