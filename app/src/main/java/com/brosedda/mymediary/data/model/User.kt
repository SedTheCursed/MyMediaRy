package com.brosedda.mymediary.data.model

import androidx.annotation.DrawableRes
import com.brosedda.mymediary.R

data class User(
    val name: String,
    val password: String? = null,
    @DrawableRes val avatar: Int = R.drawable.account_circle
)
