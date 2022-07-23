package me.danbe.common.ui.screen.mainScreen

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import me.danbe.common.data.network.Api
import me.danbe.common.data.network.common.Response


class MainViewModel(
    api: Api
):ViewModel() {

    val responseVideos = api.getVideos()
        .stateIn(viewModelScope, SharingStarted.Eagerly, Response.Loading())

}