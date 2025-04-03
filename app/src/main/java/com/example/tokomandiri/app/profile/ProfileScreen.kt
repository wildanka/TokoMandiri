package com.example.tokomandiri.app.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ProfileBottomSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("User Details", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Divider()

        UserDetailItem(label = "Name", value = "John Doe")
        UserDetailItem(label = "Username", value = "@johndoe")
        UserDetailItem(label = "Email", value = "johndoe@example.com")

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun UserDetailItem(label: String, value: String) {
    Column {
        Text(label, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(value, fontSize = 14.sp, color = Color.Gray)
    }
}