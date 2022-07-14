package com.theboringdevelopers.smartmurmansk.activity.main.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.theboringdevelopers.smartmurmansk.databinding.FragmentCreateTeamBinding
import com.theboringdevelopers.smartmurmansk.util.EventObserver
import com.theboringdevelopers.smartmurmansk.util.hideBottomNavBar
import com.theboringdevelopers.smartmurmansk.util.showBottomNavBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateTeamFragment : Fragment() {

    private lateinit var binding: FragmentCreateTeamBinding

    /** Модель из фрагмента */
    private val viewModel: CreateTeamViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateTeamBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.confirm.observe(requireActivity(), EventObserver {
            findNavController().navigate(CreateTeamFragmentDirections.actionCreateTeamFragmentToProfileFragment())
        })
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