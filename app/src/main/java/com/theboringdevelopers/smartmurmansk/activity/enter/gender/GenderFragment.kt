package com.theboringdevelopers.smartmurmansk.activity.enter.gender

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
import com.theboringdevelopers.smartmurmansk.databinding.FragmentGenderBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GenderFragment : Fragment() {

    private lateinit var binding: FragmentGenderBinding

    /** Модель из фрагмента */
    private val viewModel: GenderViewModel by viewModels()
    private val enterViewModel: EnterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGenderBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }

        binding.manIV.setOnClickListener {
            if (checkImageResource(requireContext(), it as ImageView?, R.drawable.ic_man_selected)) {
                return@setOnClickListener
            } else {
                enterViewModel.gender = true
                it.setBackgroundResource(R.drawable.ic_man_selected)
                binding.womanIV.setBackgroundResource(R.drawable.ic_woman_unselected)
            }
        }
        binding.womanIV.setOnClickListener {
            if (checkImageResource(requireContext(), it as ImageView?, R.drawable.ic_woman_selected)) {
                return@setOnClickListener
            } else {
                enterViewModel.gender = false
                it.setBackgroundResource(R.drawable.ic_woman_selected)
                binding.manIV.setBackgroundResource(R.drawable.ic_man_unselected)
            }
        }

        if (enterViewModel.gender) {
            binding.manIV.setBackgroundResource(R.drawable.ic_man_selected)
            binding.womanIV.setBackgroundResource(R.drawable.ic_woman_unselected)
        } else {
            binding.womanIV.setBackgroundResource(R.drawable.ic_woman_selected)
            binding.manIV.setBackgroundResource(R.drawable.ic_man_unselected)
        }
        return binding.root
    }

    fun checkImageResource(
        ctx: Context?, imageView: ImageView?,
        imageResource: Int,
    ): Boolean {
        var result = false
        if (ctx != null && imageView != null && imageView.drawable != null) {
            val constantState: ConstantState?
            constantState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ctx.resources
                    .getDrawable(imageResource, ctx.theme)
                    .constantState
            } else {
                ctx.resources.getDrawable(imageResource)
                    .constantState
            }
            if (imageView.drawable.constantState === constantState) {
                result = true
            }
        }
        return result
    }
}