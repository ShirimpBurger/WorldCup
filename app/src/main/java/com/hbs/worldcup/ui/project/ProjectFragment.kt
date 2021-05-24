package com.hbs.worldcup.ui.project

import android.os.Bundle
import android.view.View
import com.hbs.worldcup.R
import com.hbs.worldcup.core.BaseFragment
import com.hbs.worldcup.databinding.ProjectFragmentBinding
import com.hbs.worldcup.models.FragmentInitializer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectFragment : BaseFragment<ProjectFragmentBinding>() {

    override fun getFragmentInitializer(): FragmentInitializer = FragmentInitializer(R.layout.project_fragment)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}