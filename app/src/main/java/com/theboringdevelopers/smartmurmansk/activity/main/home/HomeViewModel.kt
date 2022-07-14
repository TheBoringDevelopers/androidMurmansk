package com.theboringdevelopers.smartmurmansk.activity.main.home

import android.content.Context
import android.os.Parcelable
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import com.theboringdevelopers.smartmurmansk.util.ui.ItemViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    @ApplicationContext context: Context,
    val userContext: UserContext
) : BaseViewModel() {

    private val _stories = MutableLiveData<List<ItemViewModel>>(emptyList())
    val stories: MutableLiveData<List<ItemViewModel>> = _stories

    val storiesValue: MutableList<StoryItemModel.Story> = mutableListOf()
    val grantsValue: MutableList<StoryItemModel.Story> = mutableListOf()

    init {
        storiesValue.add(StoryItemModel.Story(
            imageId = R.mipmap.background,
            mipmapId = R.mipmap.storynews
        ))
        storiesValue.add(StoryItemModel.Story(
            imageId = R.mipmap.background1,
            mipmapId = R.mipmap.storynews1
        ))
        storiesValue.add(StoryItemModel.Story(
            imageId = R.mipmap.background2,
            mipmapId = R.mipmap.storynews2
        ))
        storiesValue.add(StoryItemModel.Story(
            imageId = R.mipmap.background,
            mipmapId = R.mipmap.storynews
        ))

        val viewData = mutableListOf<ItemViewModel>()
        storiesValue.forEach { viewData.add(StoryItemModel(it)) }
        _stories.postValue(viewData)

        grantsValue.add(StoryItemModel.Story(
            imageId = R.mipmap.background,
            mipmapId = R.mipmap.grant
        ))
        grantsValue.add(StoryItemModel.Story(
            imageId = R.mipmap.background1,
            mipmapId = R.mipmap.grant1
        ))
        grantsValue.add(StoryItemModel.Story(
            imageId = R.mipmap.background2,
            mipmapId = R.mipmap.grant2
        ))
    }

    fun storySelected(view: View, item: ItemViewModel?) {
        if (item != null) {
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToStoriesFragment(
                StoriesWrapper(
                    storiesValue
                )
            ))
        }
    }

    fun grants(v: View) {
        v.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToStoriesFragment(
            StoriesWrapper(
                grantsValue
            )
        ))
    }

    @kotlinx.parcelize.Parcelize
    data class StoriesWrapper(
        val stories: List<StoryItemModel.Story>
    ) : Parcelable
}