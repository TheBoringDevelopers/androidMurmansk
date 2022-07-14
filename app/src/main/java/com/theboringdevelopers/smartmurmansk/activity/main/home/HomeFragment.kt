package com.theboringdevelopers.smartmurmansk.activity.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var tabTitles = arrayListOf("Моя лента", "Трансляции", "Результаты")

    /** Модель из фрагмента */
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        bindViewPagerAdapter()
        return binding.root
    }

    private fun bindViewPagerAdapter() {
        binding.pager.adapter = HomeAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        for (i in 0..2) {
            val textView = LayoutInflater.from(requireContext())
                .inflate(R.layout.tab_title_home_layout, null) as TextView
            binding.tabLayout.getTabAt(i)?.customView = textView
        }
    }
}