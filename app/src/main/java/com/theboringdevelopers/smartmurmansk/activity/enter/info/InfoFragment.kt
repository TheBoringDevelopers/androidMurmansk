package com.theboringdevelopers.smartmurmansk.activity.enter.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.activity.EnterViewModel
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.databinding.FragmentInfoBinding
import com.theboringdevelopers.smartmurmansk.util.EventObserver
import com.theboringdevelopers.smartmurmansk.util.hideKeyboard
import com.theboringdevelopers.smartmurmansk.util.showErrorSnackBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InfoFragment : Fragment() {

    @Inject
    lateinit var userContext: UserContext

    private lateinit var binding: FragmentInfoBinding

    /** Модель из фрагмента */
    private val viewModel: InfoViewModel by viewModels()
    private val enterViewModel: EnterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pager.adapter = InfoTabAdapter(this)
        binding.pager.offscreenPageLimit = 4
        binding.pager.isUserInputEnabled = false

        viewModel.changePage.observe(requireActivity(), EventObserver {
            if (viewModel.progress.get()) {
                return@EventObserver
            }
            viewModel.currentPage += 1
            if (viewModel.currentPage == 4) {
                viewModel.updateInfo(
                    enterViewModel.phoneNumber,
                    enterViewModel.firstName,
                    enterViewModel.lastName,
                    enterViewModel.patronymic,
                    enterViewModel.age,
                    enterViewModel.gender
                )
                userContext.let {
                    it.age = enterViewModel.age
                    it.name = enterViewModel.firstName
                    it.lastName = enterViewModel.lastName
                    it.gender = enterViewModel.gender
                }
                return@EventObserver
            }
            binding.progress.progress += 25
            binding.pager.currentItem = viewModel.currentPage
            if (viewModel.currentPage != 0) {
                requireActivity().hideKeyboard()
            }
            if (viewModel.currentPage == 3) {
                binding.hintTV.text = requireContext().getString(R.string.personal_preferences_title)
            } else {
                binding.hintTV.text = requireContext().getString(R.string.about)
            }
        })

        viewModel.success.observe(requireActivity(), EventObserver {
            findNavController().navigate(InfoFragmentDirections.actionInfoFragmentToMainActivity(viewModel.userContext))
            requireActivity().finish()
        })

        viewModel.back.observe(requireActivity(), EventObserver {
            processBack()
        })

        viewModel.errorMessage.observe(requireActivity(), EventObserver {
            activity?.showErrorSnackBar(it)
        })

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                processBack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun processBack() {
        if (viewModel.progress.get()) {
            return
        }
        binding.pager.currentItem = --viewModel.currentPage
        if (viewModel.currentPage == -1) {
            findNavController().popBackStack(R.id.phoneFragment, false)
        } else {
            binding.progress.progress -= 25
        }
        if (viewModel.currentPage == 3) {
            binding.hintTV.text = requireContext().getString(R.string.personal_preferences_title)
        } else {
            binding.hintTV.text = requireContext().getString(R.string.about)
        }
    }
}