package com.theboringdevelopers.smartmurmansk.activity.main.team.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.theboringdevelopers.smartmurmansk.databinding.FragmentPlayersBinding
import com.theboringdevelopers.smartmurmansk.util.EventObserver
import com.theboringdevelopers.smartmurmansk.util.showErrorSnackBar
import com.theboringdevelopers.smartmurmansk.util.showInfoSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersFragment : Fragment() {

    private lateinit var binding: FragmentPlayersBinding

    /** Модель из фрагмента */
    private val viewModel: PlayersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayersBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.infoMessage.observe(requireActivity(), EventObserver {
            activity?.showInfoSnackBar(it)
        })

        viewModel.errorMessage.observe(requireActivity(), EventObserver {
            activity?.showErrorSnackBar(it)
        })
    }
}