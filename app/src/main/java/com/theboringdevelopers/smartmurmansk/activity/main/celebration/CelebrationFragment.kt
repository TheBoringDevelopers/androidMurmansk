package com.theboringdevelopers.smartmurmansk.activity.main.celebration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.theboringdevelopers.smartmurmansk.databinding.FragmentCelebrationBinding
import com.theboringdevelopers.smartmurmansk.util.hideBottomNavBar
import com.theboringdevelopers.smartmurmansk.util.showBottomNavBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CelebrationFragment : Fragment() {


    private lateinit var binding: FragmentCelebrationBinding

    /** Модель из фрагмента */
    private val viewModel: CelebrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCelebrationBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        hideBottomNavBar(requireActivity())
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        showBottomNavBar(requireActivity())
    }
}