package com.brosedda.mymediary.tutorials.mars.fake

import com.brosedda.mymediary.tutorials.mars.data.MarsPhotoRepository
import com.brosedda.mymediary.tutorials.mars.network.MarsPhoto

class FakeMarsPhotoRepository: MarsPhotoRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> = FakeDataSource.photos
}