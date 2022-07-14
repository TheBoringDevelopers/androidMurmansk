package com.theboringdevelopers.smartmurmansk.activity.enter.info

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.theboringdevelopers.smartmurmansk.activity.enter.age.AgeFragment
import com.theboringdevelopers.smartmurmansk.activity.enter.fio.FioFragment
import com.theboringdevelopers.smartmurmansk.activity.enter.gender.GenderFragment
import com.theboringdevelopers.smartmurmansk.activity.enter.personal_preferences.PersonalPreferencesFragment

class InfoTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FioFragment()
            }
            1 -> {
                GenderFragment()
            }
            2 -> {
                AgeFragment()
            }
            else -> {
                PersonalPreferencesFragment()
            }
        }
    }

}