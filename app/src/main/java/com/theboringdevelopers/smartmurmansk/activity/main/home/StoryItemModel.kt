package com.theboringdevelopers.smartmurmansk.activity.main.home

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.view.View
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.util.ui.ItemViewModel

data class StoryItemModel(
    val story: Story
) : ItemViewModel {

    override val layoutId: Int
        get() = R.layout.item_story

    fun getImage(context: Context): Drawable {
        return context.getDrawable(story.imageId!!)!!
    }

    @kotlinx.parcelize.Parcelize
    data class Story(
        val imageId: Int? = null,
        val mipmapId: Int
    ): Parcelable
}