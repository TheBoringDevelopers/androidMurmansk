package com.theboringdevelopers.smartmurmansk.activity.main.team.ura

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.theboringdevelopers.smartmurmansk.databinding.FragmentCreatedTeamBinding
import com.theboringdevelopers.smartmurmansk.util.hideBottomNavBar
import com.theboringdevelopers.smartmurmansk.util.showBottomNavBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CelebrationTeamFragment : Fragment() {

    private lateinit var binding: FragmentCreatedTeamBinding

    /** Модель из фрагмента */
    private val viewModel: CelebrationTeamViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatedTeamBinding
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