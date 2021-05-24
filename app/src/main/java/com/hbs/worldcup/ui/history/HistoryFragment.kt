package com.hbs.worldcup.ui.history

import android.os.Bundle
import android.view.View
import com.hbs.worldcup.R
import com.hbs.worldcup.core.BaseFragment
import com.hbs.worldcup.databinding.HistoryFragmentBinding
import com.hbs.worldcup.models.FragmentInitializer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<HistoryFragmentBinding>() {

    override fun getFragmentInitializer(): FragmentInitializer = FragmentInitializer(R.layout.history_fragment)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}