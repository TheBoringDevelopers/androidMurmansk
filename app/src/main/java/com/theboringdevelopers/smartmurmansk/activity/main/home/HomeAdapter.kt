package com.theboringdevelopers.smartmurmansk.activity.main.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.theboringdevelopers.smartmurmansk.activity.main.home.feed.FeedFragment
import com.theboringdevelopers.smartmurmansk.activity.main.team.players.PlayersFragment
import com.theboringdevelopers.smartmurmansk.activity.main.team.teams.TeamsFragment

class HomeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FeedFragment()
            1 -> FeedFragment()
            else -> FeedFragment()
        }
    }
}