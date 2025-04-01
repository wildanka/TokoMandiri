package com.example.tokomandiri.app.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onShowProductDetail: (Int) -> Unit) {
    Box {
        Text("Home Screen")
    }
}