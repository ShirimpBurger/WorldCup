package com.hbs.worldcup.ui.quizlist

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.hbs.domain.model.core.ActivityInitializer
import com.hbs.worldcup.R
import com.hbs.worldcup.core.BaseActivity
import com.hbs.worldcup.databinding.QuizListActivityBinding
import com.hbs.worldcup.ui.quiz.QuizActivity
import com.hbs.worldcup.ui.dashboard.alarm.AlarmDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizListActivity : BaseActivity<QuizListActivityBinding>() {
    private val quizListViewModel by viewModels<QuizListViewModel>()
    private val largeCardRecommendAdapter by lazy { LargeCardRecommendAdapter(recommendClickCallback) }

    override fun getActivityInitializer() = ActivityInitializer(R.layout.quiz_list_activity, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViewModel()
        bindView()
        observeViewModel()
    }

    private fun bindViewModel() {
        binding.viewModel = quizListViewModel
    }

    private fun bindView() {
        with(binding.largeCardRecommendRecyclerview) {
            adapter = largeCardRecommendAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        }
        with(binding.bottomAppBar) {
            setOnMenuItemClickListener { item: MenuItem? ->
                toggleDarkTheme()
                item?.let { toggleDarkThemeMenuIcon(it) }
                true
            }
            toggleDarkThemeMenuIcon(menu.findItem(R.id.item_darktheme))
        }
        binding.callback = Callback {
            AlarmDialog().show(supportFragmentManager, "ALARM_DIALOG")

        }
    }

    private fun observeViewModel() {
        quizListViewModel.largeCardRecommends.observe(this, {
            largeCardRecommendAdapter.submitList(it)
        })
    }

    private val recommendClickCallback =
        LargeCardRecommendAdapter.Callback { largeCardRecommend, _ ->
            startActivity(Intent(this, QuizActivity::class.java))
        }

    private fun checkDarkTheme(): Boolean {
        val defaultNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return defaultNightMode == Configuration.UI_MODE_NIGHT_YES
    }

    private fun toggleDarkTheme() {
        if (checkDarkTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    @Suppress("DEPRECATION")
    private fun toggleDarkThemeMenuIcon(menuItem: MenuItem) {
        if (checkDarkTheme()) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_moon_color)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_moon)
        }
    }
}

fun interface Callback {
    fun onClick()
}