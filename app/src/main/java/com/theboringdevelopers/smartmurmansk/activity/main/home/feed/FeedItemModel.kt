package com.theboringdevelopers.smartmurmansk.activity.main.home.feed

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.view.View
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.util.ui.ItemViewModel

data class FeedItemModel(
    val feed: FeedViewModel.Feed
) : ItemViewModel {

    override val layoutId: Int
        get() = R.layout.item_feed

    fun getImage(context: Context): Drawable {
        return context.getDrawable(feed.imageId!!)!!
    }
}