package com.appmosphere.chat.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.appmosphere.chat.model.Message
import java.util.*

class ChatViewModel : ViewModel() {
    private val _messages = mutableStateListOf<Message>()
    val messages: List<Message> get() = _messages

    var sortDescending by mutableStateOf(true)
    var replyTarget by mutableStateOf<Message?>(null)

    fun sendMessage(text: String) {
        val newMsg = Message(text = text, replyTo = replyTarget)
        _messages.add(newMsg)
        replyTarget = null
        sortMessages()
    }

    fun sortMessages() {
        _messages.sortBy { if (sortDescending) it.timestamp else -it.timestamp }
    }

    fun updateReplyTarget(msg: Message) {
        replyTarget = msg
    }

    fun clearReply() {
        replyTarget = null
    }

    fun toggleSortOrder() {
        sortDescending = !sortDescending
        sortMessages()
    }
}
