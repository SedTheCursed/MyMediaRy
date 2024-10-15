package com.brosedda.mymediary.tutorials.mars.fake

import com.brosedda.mymediary.tutorials.mars.network.MarsApiService
import com.brosedda.mymediary.tutorials.mars.network.MarsPhoto

class FakeMarsApiService: MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> = FakeDataSource.photos
}