package com.theboringdevelopers.smartmurmansk.activity.main.home.feed

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.activity.main.team.teams.TeamItemModel
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import com.theboringdevelopers.smartmurmansk.util.Event
import com.theboringdevelopers.smartmurmansk.util.ui.ItemViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel@Inject constructor(

) : BaseViewModel() {

    private val _data = MutableLiveData<List<ItemViewModel>>(emptyList())
    val data: LiveData<List<ItemViewModel>> = _data

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val viewData = mutableListOf<ItemViewModel>()

            viewData.add(FeedItemModel(
                Feed(
                    "вчера",
                    "Разминка 3x3",
                    "Регистрируем заявки на разминку команд перед...",
                    R.mipmap.feed1
                )
            ))

            viewData.add(FeedItemModel(
                Feed(
                    "2 дня назад",
                    "Трекер активности",
                    "Подключите отслеживание активности",
                    R.mipmap.feed2
                )
            ))

            _data.postValue(viewData)
        }
    }

    data class Feed(
        val first: String,
        val second: String,
        val third: String,
        val imageId: Int
    )

}