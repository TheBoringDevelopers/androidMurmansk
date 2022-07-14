package com.theboringdevelopers.smartmurmansk.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.theboringdevelopers.smartmurmansk.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Стартовая активность приложения
 */
@AndroidEntryPoint
class EnterActivity : AppCompatActivity() {

    private val viewModel: EnterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)
    }
}