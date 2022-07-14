package com.theboringdevelopers.smartmurmansk.activity.enter.fio

import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FioViewModel@Inject constructor(

) : BaseViewModel() {

    val data = FioViewData()

}