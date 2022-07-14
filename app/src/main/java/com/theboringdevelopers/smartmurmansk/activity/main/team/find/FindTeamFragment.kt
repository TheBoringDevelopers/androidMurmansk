package com.theboringdevelopers.smartmurmansk.activity.main.team.find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.databinding.FragmentFindTeamBinding
import com.theboringdevelopers.smartmurmansk.util.hideBottomNavBar
import com.theboringdevelopers.smartmurmansk.util.showBottomNavBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindTeamFragment : Fragment() {

    private lateinit var binding: FragmentFindTeamBinding

    /** Модель из фрагмента */
    private val viewModel: FindTeamViewModel by viewModels()
    private val tabTitles = arrayListOf("Команды", "Участники")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFindTeamBinding
            .inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        setUpTabLayoutWithViewPager()
        return binding.root
    }

    private fun setUpTabLayoutWithViewPager() {
        binding.pager.adapter = FindTeamAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        for (i in 0..3) {
            val textView = LayoutInflater.from(requireContext())
                .inflate(R.layout.tab_title_layout, null) as TextView
            binding.tabLayout.getTabAt(i)?.customView = textView
        }
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        hideBottomNavBar(requireActivity())
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        showBottomNavBar(requireActivity())
    }
}