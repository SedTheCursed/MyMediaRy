package com.brosedda.mymediary.ui.viewModel

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.brosedda.mymediary.data.avatars as avatarsSrc

class AvatarViewModel: ViewModel() {
    val avatars = avatarsSrc

    var selected by mutableIntStateOf(avatars[0])
    private set

    fun selectAvatar(@DrawableRes avatar: Int) {
        selected = avatar
    }
}
