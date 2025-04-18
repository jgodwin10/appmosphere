package com.appmosphere.chat.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.appmosphere.chat.model.Message
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessageItem(message: Message, onReply: (Message) -> Unit) {
    val isUserMessage = message.id.hashCode() % 2 == 0
    val backgroundColor = if (isUserMessage) Color(0xFFD0E8FF) else Color(0xFFEAEAEA)
    val alignment = if (isUserMessage) Alignment.End else Alignment.Start

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        AnimatedVisibility(visible = true) {
            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .background(backgroundColor, shape = MaterialTheme.shapes.medium)
                    .padding(12.dp)
                    .widthIn(max = 300.dp)
            ) {
                if (message.replyTo != null) {
                    Text(
                        text = "↪ ${message.replyTo.text.take(40)}",
                        style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(message.timestamp)),
                    style = MaterialTheme.typography.labelSmall.copy(color = Color.DarkGray),
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        TextButton(onClick = { onReply(message) }) {
            Text("Reply", style = MaterialTheme.typography.labelSmall)
        }
    }
}
