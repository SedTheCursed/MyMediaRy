package com.brosedda.mymediary.tutorials.reply.ui

import com.brosedda.mymediary.tutorials.reply.data.Email
import com.brosedda.mymediary.tutorials.reply.data.MailboxType
import com.brosedda.mymediary.tutorials.reply.data.local.LocalEmailsDataProvider

data class ReplyUiState(
    val mailboxes: Map<MailboxType, List<Email>> = emptyMap(),
    val currentMailbox: MailboxType = MailboxType.Inbox,
    val currentSelectedEmail: Email = LocalEmailsDataProvider.defaultEmail,
    val isShowingHomepage: Boolean = true
) {
    val currentMailboxEmails: List<Email> by lazy { mailboxes[currentMailbox]!! }
}
