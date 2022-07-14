package com.theboringdevelopers.smartmurmansk.activity.enter.fio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.theboringdevelopers.smartmurmansk.activity.EnterViewModel
import com.theboringdevelopers.smartmurmansk.databinding.FragmentFioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FioFragment : Fragment() {

    private lateinit var binding: FragmentFioBinding

    /** Модель из фрагмента */
    private val viewModel: FioViewModel by viewModels()
    private val enterViewModel: EnterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFioBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }

    override fun onPause() {
        super.onPause()
        enterViewModel.lastName = viewModel.data.lastName
        enterViewModel.firstName = viewModel.data.name
        enterViewModel.patronymic = viewModel.data.patronymic
    }
}