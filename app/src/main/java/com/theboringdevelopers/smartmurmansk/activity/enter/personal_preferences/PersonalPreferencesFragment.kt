package com.theboringdevelopers.smartmurmansk.activity.enter.personal_preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.databinding.FragmentPersonalPreferencesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PersonalPreferencesFragment : Fragment() {

    private lateinit var binding: FragmentPersonalPreferencesBinding

    /** Модель из фрагмента */
    private val viewModel: PersonalPreferencesViewModel by viewModels()

    val titles = arrayListOf("", "", "", "", "", "", "", "")
    val images = arrayListOf(R.mipmap.bubble_test, R.mipmap.bubble_test, R.mipmap.bubble_test, R.mipmap.bubble_test, R.mipmap.bubble_test, R.mipmap.bubble_test, R.mipmap.bubble_test, R.mipmap.bubble_test)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPersonalPreferencesBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }

//        binding.picker.adapter = object : BubblePickerAdapter {
//
//            override val totalCount = titles.size
//
//            override fun getItem(position: Int): PickerItem {
//                return PickerItem().apply {
//                    title = titles[position]
//                    typeface = ResourcesCompat.getFont(requireContext(), R.font.manrope_regular)!!
//                    textColor = ContextCompat.getColor(requireContext(), R.color.select)
//                    textSize = 12F
//                    gradient = BubbleGradient(Color.parseColor("#8E9FB8"),
//                        Color.parseColor("#0D112C"), BubbleGradient.VERTICAL)
//                    backgroundImage = ContextCompat.getDrawable(requireContext(), images.get(position))
//                    color = Color.parseColor("#00A5E1")
//                }
//            }
//        }
//
//        binding.picker.centerImmediately = true

        return binding.root
    }

    override fun onResume() {
        super.onResume()
//        binding.picker.onResume()
    }

    override fun onPause() {
        super.onPause()
//        binding.picker.onPause()
    }
}