package com.theboringdevelopers.smartmurmansk.activity.main.home.stories

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.theboringdevelopers.smartmurmansk.activity.main.home.StoryItemModel
import com.theboringdevelopers.smartmurmansk.activity.main.home.stories.story.StoryFragment

class StoriesTabAdapter(
    fragment: Fragment,
    private val items: List<StoryItemModel.Story>
    ) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return StoryFragment(items[position])
    }

}