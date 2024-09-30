package com.brosedda.mymediary.ui.test

import com.brosedda.mymediary.ui.viewModel.users.AvatarViewModel
import com.brosedda.mymediary.R
import org.junit.Assert.assertEquals
import org.junit.Test

class AvatarViewModelTest {
    private val viewModel = AvatarViewModel()

    @Test
    fun avatarViewModel_ImageChosen_selectedSetImageValue() {
        val image = R.drawable.add_circle

        viewModel.selectAvatar(image)
        assertEquals(image, viewModel.selected)
    }
}