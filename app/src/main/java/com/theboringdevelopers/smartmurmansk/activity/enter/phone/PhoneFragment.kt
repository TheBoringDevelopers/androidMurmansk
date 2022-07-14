package com.theboringdevelopers.smartmurmansk.activity.enter.phone

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.activity.EnterViewModel
import com.theboringdevelopers.smartmurmansk.data.repository.AuthRepository
import com.theboringdevelopers.smartmurmansk.databinding.FragmentPhoneBinding
import com.theboringdevelopers.smartmurmansk.util.*
import com.theboringdevelopers.smartmurmansk.util.ui.CustomPhoneWatcher
import com.theboringdevelopers.smartmurmansk.util.ui.PhoneTextFilter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneFragment : Fragment() {

    private lateinit var binding: FragmentPhoneBinding

    /** Модель из фрагмента */
    private val viewModel: PhoneViewModel by viewModels()
    private val enterViewModel: EnterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhoneBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginResult.observe(requireActivity(),
            EventObserver { response: AuthRepository.LoginResponse ->
                if (response.success && response.registrationResponse != null) {
                    findNavController().navigate(PhoneFragmentDirections.actionPhoneFragmentToCodeFragment(
                        response.registrationResponse.session,
                        viewModel.preparePhone(viewModel.data.login)))
                    enterViewModel.phoneNumber = viewModel.preparePhone(viewModel.data.login)
                    activity?.showInfoSnackBar(response.registrationResponse.code)
                    requireActivity().hideKeyboard()
                } else if (response.success) {
                    requireActivity().hideKeyboard()
                    findNavController().navigate(PhoneFragmentDirections.actionPhoneFragmentToMainActivity(
                        response.userContext))
                }
            })

        viewModel.errorMessage.observe(requireActivity(), EventObserver {
            activity?.showErrorSnackBar(it)
        })

        viewModel.infoMessage.observe(requireActivity(), EventObserver {
            activity?.showInfoSnackBar(it)
        })

        initViews()
    }

    private fun initViews() {
        binding.phoneET.setText("+7")
        binding.phoneET.addTextChangedListener(CustomPhoneWatcher(binding.phoneET))
        binding.phoneET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    binding.phoneTW.text =
                        s.toString() + requireContext().getText(R.string.phone_mask).substring(s.length)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.phoneET.filters = arrayOf(InputFilter.LengthFilter(16), PhoneTextFilter())

        binding.phoneET.setOnEditorActionListener(
            TextView.OnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.login(null)
                    binding.phoneET.hideKeyboard()
                    return@OnEditorActionListener true
                }
                false
            })

        binding.phoneET.requestFocusShowKeyboard()
    }
}