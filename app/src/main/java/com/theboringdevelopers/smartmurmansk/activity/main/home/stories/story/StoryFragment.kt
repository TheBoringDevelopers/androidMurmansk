package com.theboringdevelopers.smartmurmansk.activity.main.home.stories.story

import android.content.Context
import android.graphics.drawable.Drawable.ConstantState
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.activity.EnterViewModel
import com.theboringdevelopers.smartmurmansk.activity.main.home.StoryItemModel
import com.theboringdevelopers.smartmurmansk.databinding.FragmentGenderBinding
import com.theboringdevelopers.smartmurmansk.databinding.FragmentStoryBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StoryFragment(val story: StoryItemModel.Story) : Fragment() {

    private lateinit var binding: FragmentStoryBinding

    /** Модель из фрагмента */
    private val viewModel: StoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentStoryBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.passStory(story)
    }

}