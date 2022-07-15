package com.theboringdevelopers.smartmurmansk.activity.main.home.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.theboringdevelopers.smartmurmansk.databinding.FragmentFeedBinding
import com.theboringdevelopers.smartmurmansk.databinding.FragmentTeamsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private lateinit var binding: FragmentFeedBinding

    /** Модель из фрагмента */
    private val viewModel: FeedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}