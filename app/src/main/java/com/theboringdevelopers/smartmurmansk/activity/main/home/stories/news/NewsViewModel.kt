package com.theboringdevelopers.smartmurmansk.activity.main.home.stories.news

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.activity.main.home.StoryItemModel
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import com.theboringdevelopers.smartmurmansk.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class NewsViewModel@Inject constructor(
    @ApplicationContext val context: Context
) : BaseViewModel() {

    private val _storyData = MutableLiveData<StoryItemModel.Story>()
    val storyData: LiveData<StoryItemModel.Story> = _storyData

    fun passStory(story: StoryItemModel.Story) {
        _storyData.postValue(story)
    }


}