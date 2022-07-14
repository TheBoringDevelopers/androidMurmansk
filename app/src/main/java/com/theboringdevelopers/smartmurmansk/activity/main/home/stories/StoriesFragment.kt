package com.theboringdevelopers.smartmurmansk.activity.main.home.stories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.activity.EnterViewModel
import com.theboringdevelopers.smartmurmansk.activity.main.home.stories.news.NewsTabAdapter
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.databinding.FragmentInfoBinding
import com.theboringdevelopers.smartmurmansk.databinding.FragmentStoriesBinding
import com.theboringdevelopers.smartmurmansk.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class StoriesFragment : Fragment() {

    @Inject
    lateinit var userContext: UserContext

    private lateinit var binding: FragmentStoriesBinding

    /** Модель из фрагмента */
    private val viewModel: StoriesViewModel by viewModels()

    lateinit var job: Job

    private val args: StoriesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoriesBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }

        binding.leftLL.setOnClickListener {
            job.cancel()
            if (viewModel.currentPage == 0) {
                binding.progress1.progress = 0
            } else if (viewModel.currentPage == 1) {
                binding.pager.currentItem = --viewModel.currentPage
                binding.progress2.progress = 0
            } else if (viewModel.currentPage == 2) {
                binding.pager.currentItem = --viewModel.currentPage
                binding.progress3.progress = 0
            }
            startJob()
        }

        binding.rightLL.setOnClickListener {
            job.cancel()
            if (viewModel.currentPage == 0) {
                binding.progress1.progress = 100
                binding.pager.currentItem = ++viewModel.currentPage
            } else if (viewModel.currentPage == 1) {
                binding.progress2.progress = 100
                binding.pager.currentItem = ++viewModel.currentPage
            } else if (viewModel.currentPage == 2) {
                binding.progress3.progress = 100
                binding.pager.currentItem = ++viewModel.currentPage
                findNavController().popBackStack()
            }
            startJob()
        }

        binding.bottomLL.setOnClickListener {
            job.cancel()
            binding.slidingLayout.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
        }

        binding.closeIV.setOnClickListener {
            job.cancel()
            findNavController().popBackStack()
        }

        binding.slidingLayout.coveredFadeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
        binding.slidingLayout.setFadeOnClickListener {
            binding.slidingLayout.panelState = SlidingUpPanelLayout.PanelState.HIDDEN
        }

        binding.slidingLayout.addPanelSlideListener(object :
            SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View, slideOffset: Float) {}
            override fun onPanelStateChanged(
                panel: View,
                previousState: SlidingUpPanelLayout.PanelState,
                newState: SlidingUpPanelLayout.PanelState
            ) {
                if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    binding.slidingLayout.panelState = SlidingUpPanelLayout.PanelState.HIDDEN
                }
                if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    job.cancel()
                }
                if (newState == SlidingUpPanelLayout.PanelState.HIDDEN) {
                    startJob()
                }
            }
        })

        binding.dragImageView.setOnClickListener {
            binding.slidingLayout.panelState = SlidingUpPanelLayout.PanelState.HIDDEN
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newsPager.adapter = NewsTabAdapter(this, args.storiesWrapper.stories)
        binding.newsPager.offscreenPageLimit = 3
        binding.newsPager.isUserInputEnabled = false

        binding.pager.adapter = StoriesTabAdapter(this, args.storiesWrapper.stories)
        binding.pager.offscreenPageLimit = 3
        binding.pager.isUserInputEnabled = false

        startJob()

        viewModel.errorMessage.observe(requireActivity(), EventObserver {
            activity?.showErrorSnackBar(it)
        })
    }

    private fun startJob() {
        job = CoroutineScope(Dispatchers.IO).launch {
            while (viewModel.currentPage != 3 && isActive) {
                val zero: Double = System.currentTimeMillis().toDouble()
                val storyTime: Double = zero + 3000
                var dif: Double = storyTime - zero
                while (dif > 0 && isActive) {
                    val progress = ((1 - (dif / 3000)) * 100).toInt()
                    if (isActive) {
                        if (viewModel.currentPage == 0) {
                            binding.progress1.progress = progress
                        } else if (viewModel.currentPage == 1) {
                            binding.progress2.progress = progress
                        } else if (viewModel.currentPage == 2) {
                            binding.progress3.progress = progress
                        }
                        dif = storyTime - System.currentTimeMillis()
                    }
                }
                if (isActive) {
                    if (viewModel.currentPage == 0) {
                        binding.progress1.progress = 100
                    } else if (viewModel.currentPage == 1) {
                        binding.progress2.progress = 100
                    } else if (viewModel.currentPage == 2) {
                        binding.progress3.progress = 100
                    }
                    binding.pager.currentItem = ++viewModel.currentPage
                }
            }
            if (isActive) {
                withContext(Dispatchers.Main) {
                    findNavController().popBackStack()
                }
            }

        }
    }


    override fun onStart() {
        super.onStart()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        hideBottomNavBar(requireActivity())
    }

    override fun onStop() {
        super.onStop()
        job.cancel()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        showBottomNavBar(requireActivity())
    }


}