package com.brosedda.mymediary.tutorials.reply.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brosedda.mymediary.tutorials.reply.data.Email
import com.brosedda.mymediary.tutorials.reply.data.MailboxType
import com.brosedda.mymediary.tutorials.reply.ui.utils.ReplyContentType
import com.brosedda.mymediary.tutorials.reply.ui.utils.ReplyNavigationType

@Composable
fun ReplyApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val viewModel: ReplyViewModel = viewModel()
    val replyUiState = viewModel.uiState.collectAsState().value

    val navigationType = when (windowSize) {
        WindowWidthSizeClass.Compact -> ReplyNavigationType.BOTTOM_NAVIGATION
        WindowWidthSizeClass.Medium -> ReplyNavigationType.NAVIGATION_RAIL
        WindowWidthSizeClass.Expanded -> ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
        else -> ReplyNavigationType.BOTTOM_NAVIGATION
    }

    val contentType = when (windowSize) {
        WindowWidthSizeClass.Expanded -> ReplyContentType.LIST_AND_DETAIL
        else -> ReplyContentType.LIST_ONLY
    }

    ReplyHomeScreen(
        replyUiState = replyUiState,
        navigationType = navigationType,
        contentType = contentType,
        onTabPressed = { mailboxType: MailboxType ->
            viewModel.updateCurrentMailbox(mailboxType = mailboxType)
            viewModel.resetHomeScreenStates()
        },
        onEmailCardPressed = { email: Email ->
            viewModel.updateDetailsScreenStates(
                email = email
            )
        },
        onDetailScreenBackPressed = {
            viewModel.resetHomeScreenStates()
        },
        modifier = modifier
    )
}