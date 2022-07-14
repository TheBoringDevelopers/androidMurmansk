package com.theboringdevelopers.smartmurmansk.activity.enter.code

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import com.theboringdevelopers.smartmurmansk.databinding.FragmentCodeBinding
import com.theboringdevelopers.smartmurmansk.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CodeFragment : Fragment() {

    private lateinit var binding: FragmentCodeBinding

    /** Модель из фрагмента */
    private val viewModel: CodeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCodeBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }

        binding.smsCodeView.onChangeListener =
            SmsConfirmationView.OnChangeListener { code, isComplete ->
                if (isComplete) {
                    viewModel.confirm(code)
                }
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.confirm.observe(requireActivity(), EventObserver {
            requireActivity().hideKeyboard()
            findNavController().navigate(CodeFragmentDirections.actionCodeFragmentToInfoFragment())
        })

        viewModel.errorMessage.observe(requireActivity(), EventObserver {
            activity?.showErrorSnackBar(it)
        })

        viewModel.infoMessage.observe(requireActivity(), EventObserver {
            activity?.showInfoSnackBar(it)
        })

        binding.smsCodeView.requestFocusShowKeyboard()

    }
}