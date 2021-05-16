package com.hbs.worldcup.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hbs.worldcup.databinding.CreateFragmentBinding
import com.hbs.worldcup.databinding.ProjectFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = CreateFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}