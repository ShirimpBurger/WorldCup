package com.hbs.worldcup.ui.dashboard.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hbs.domain.model.AlarmItem
import com.hbs.worldcup.R
import com.hbs.worldcup.databinding.AlarmDialogLayoutBinding

class AlarmDialog : BottomSheetDialogFragment() {
    private lateinit var binding: AlarmDialogLayoutBinding
    private val alarmListAdapter = AlarmListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.RadiusBottomSheet)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AlarmDialogLayoutBinding.inflate(inflater)
        binding.alarmRecyclerview.adapter = alarmListAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alarmListAdapter.submitList(makeAlarmItems()) {
            setExpandDialog()
        }
    }

    private fun setExpandDialog(){
        dialog?.let {
            val bottomSheetDialog = dialog as BottomSheetDialog
            bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
    private fun makeAlarmItems() = listOf(
        AlarmItem("Hello", "World!"),
        AlarmItem("Hello2", "World!"),
        AlarmItem("Hello3", "World!"),
        AlarmItem("Hello4", "World!")
    )
}