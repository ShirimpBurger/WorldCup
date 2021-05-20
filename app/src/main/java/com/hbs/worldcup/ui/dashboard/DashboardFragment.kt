package com.hbs.worldcup.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hbs.worldcup.databinding.DashboardFragmentBinding
import com.hbs.worldcup.ui.quiz.QuizActivity
import com.hbs.worldcup.ui.dashboard.alarm.AlarmDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding:DashboardFragmentBinding
    private val viewModel by viewModels<DashboardViewModel>()
    private val listAdapter by lazy { DashboardListAdapter(clickCallback) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DashboardFragmentBinding.inflate(layoutInflater)

        bindViewModel()
        bindView()
        observeViewModel()
        return binding.root
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