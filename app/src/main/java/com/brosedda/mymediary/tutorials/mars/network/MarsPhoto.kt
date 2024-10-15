package com.brosedda.mymediary.tutorials.mars.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsPhoto(
    val id: String,

    @SerialName(value = "img_src")
    val source: String
)