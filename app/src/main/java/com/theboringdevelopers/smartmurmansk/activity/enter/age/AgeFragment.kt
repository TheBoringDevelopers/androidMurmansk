package com.theboringdevelopers.smartmurmansk.activity.enter.age

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.theboringdevelopers.smartmurmansk.activity.EnterViewModel
import com.theboringdevelopers.smartmurmansk.databinding.FragmentAgeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgeFragment : Fragment() {

    private lateinit var binding: FragmentAgeBinding

    /** Модель из фрагмента */
    private val viewModel: AgeViewModel by viewModels()
    private val enterViewModel: EnterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAgeBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }

        binding.numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            enterViewModel.age = newVal.toLong()
        }
        return binding.root
    }
}