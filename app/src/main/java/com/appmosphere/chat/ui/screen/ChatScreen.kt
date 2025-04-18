package com.appmosphere.chat.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appmosphere.chat.ui.components.*
import com.appmosphere.chat.viewModel.ChatViewModel

@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel()) {
    val messages = viewModel.messages
    val replyTarget = viewModel.replyTarget

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        SortToggle(viewModel.sortDescending) { viewModel.toggleSortOrder() }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages) { message ->
                MessageItem(message, onReply = { viewModel.updateReplyTarget(message) })
            }
        }

        if (replyTarget != null) {
            ReplyBanner(replyTarget.text, onCancel = { viewModel.clearReply() })
        }

        MessageInput(onSend = { viewModel.sendMessage(it) })
    }
}
