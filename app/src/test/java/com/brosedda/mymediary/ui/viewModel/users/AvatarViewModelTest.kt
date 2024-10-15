package com.brosedda.mymediary.ui.viewModel.users

import com.brosedda.mymediary.R
import org.junit.Assert.assertEquals
import org.junit.Test

class AvatarViewModelTest {
    private val viewModel = AvatarViewModel()

    @Test
    fun avatarViewModel_imageChosen_selectedSetImageValue() {
        val image = R.drawable.add_circle

        viewModel.selectAvatar(image)
        assertEquals(image, viewModel.selected)
    }
}