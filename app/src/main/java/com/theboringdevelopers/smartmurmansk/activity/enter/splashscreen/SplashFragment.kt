package com.theboringdevelopers.smartmurmansk.activity.enter.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.context.UserContext
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Фрагмент стартового экрана
 */
@AndroidEntryPoint
class SplashFragment : Fragment() {

    @Inject
    lateinit var userContext: UserContext

    /** Модель из фрагмента */
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}