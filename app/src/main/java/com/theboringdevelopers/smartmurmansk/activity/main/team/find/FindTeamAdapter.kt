package com.theboringdevelopers.smartmurmansk.activity.main.team.find

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.theboringdevelopers.smartmurmansk.activity.main.team.players.PlayersFragment
import com.theboringdevelopers.smartmurmansk.activity.main.team.teams.TeamsFragment

class FindTeamAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TeamsFragment()
            else -> PlayersFragment()
        }
    }
}