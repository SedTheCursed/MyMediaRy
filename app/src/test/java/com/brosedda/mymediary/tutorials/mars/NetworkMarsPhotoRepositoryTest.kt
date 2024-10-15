package com.brosedda.mymediary.tutorials.mars

import com.brosedda.mymediary.tutorials.mars.data.NetworkMarsPhotoRepository
import com.brosedda.mymediary.tutorials.mars.fake.FakeDataSource
import com.brosedda.mymediary.tutorials.mars.fake.FakeMarsApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkMarsPhotoRepositoryTest {
    @Test
    fun networkMarsRepository_getMarsPhotos_VerifyPhotoList() = runTest {
        val repository = NetworkMarsPhotoRepository(
            marsApiService = FakeMarsApiService()
        )

        assertEquals(FakeDataSource.photos, repository.getMarsPhotos())
    }
}