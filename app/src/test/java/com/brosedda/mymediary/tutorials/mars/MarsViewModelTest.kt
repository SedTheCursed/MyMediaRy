package com.brosedda.mymediary.tutorials.mars

import com.brosedda.mymediary.rules.TestDispatcherRule
import com.brosedda.mymediary.tutorials.mars.fake.FakeDataSource
import com.brosedda.mymediary.tutorials.mars.fake.FakeMarsPhotoRepository
import com.brosedda.mymediary.tutorials.mars.ui.screens.MarsUiState
import com.brosedda.mymediary.tutorials.mars.ui.screens.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest {
        val marsViewModel = MarsViewModel(
            repository = FakeMarsPhotoRepository()
        )

        assertEquals(
            MarsUiState.Success(FakeDataSource.photos),
            marsViewModel.marsUiState
        )
    }
}