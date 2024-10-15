package com.brosedda.mymediary.tutorials.mars.data

import com.brosedda.mymediary.tutorials.mars.network.MarsApiService
import com.brosedda.mymediary.tutorials.mars.network.MarsPhoto

interface MarsPhotoRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotoRepository(
    private val marsApiService: MarsApiService
): MarsPhotoRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto>  = marsApiService.getPhotos()
}