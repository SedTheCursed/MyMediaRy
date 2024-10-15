package com.brosedda.mymediary.tutorials.mars.fake

import com.brosedda.mymediary.tutorials.mars.network.MarsPhoto

object FakeDataSource {
    val photos = listOf(
        MarsPhoto("1", "url1"),
        MarsPhoto("2","url2")
    )
}