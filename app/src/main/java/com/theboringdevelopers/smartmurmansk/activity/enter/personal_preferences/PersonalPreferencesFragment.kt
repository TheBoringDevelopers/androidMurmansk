package com.theboringdevelopers.smartmurmansk.activity.enter.personal_preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.databinding.FragmentPersonalPreferencesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PersonalPreferencesFragment : Fragment() {

    private lateinit var binding: FragmentPersonalPreferencesBinding

    /** Модель из фрагмента */
    private val viewModel: PersonalPreferencesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPersonalPreferencesBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }


        return binding.root
    }
}