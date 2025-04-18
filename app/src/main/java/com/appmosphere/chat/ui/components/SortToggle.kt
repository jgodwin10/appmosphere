package com.appmosphere.chat.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert

@Composable
fun SortToggle(isDescending: Boolean, onToggle: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text("Sort: ${if (!isDescending) "Newest" else "Oldest"}")
        IconButton(onClick = onToggle) {
            Icon(Icons.Default.SwapVert, contentDescription = "Toggle Sort")
        }
    }
}
