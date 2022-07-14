package com.theboringdevelopers.smartmurmansk.activity.main.dialog

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import com.theboringdevelopers.smartmurmansk.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MoreSheetViewModel@Inject constructor(
    @ApplicationContext private val context: Context,
    private val userContext: UserContext
) : BaseViewModel() {

    val done: MutableLiveData<Event<Any>> = MutableLiveData<Event<Any>>()


}