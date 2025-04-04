package com.example.tokomandiri.app.profile

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.security.crypto.EncryptedSharedPreferences
import com.example.tokomandiri.R
import com.example.tokomandiri.framework.AppUtility
import org.koin.core.context.GlobalContext


@Composable
fun ProfileBottomSheetContent(onLogout: () -> Unit) {
    val encryptedSharedPreferences: SharedPreferences = GlobalContext.get().get()
    val encryptedPrefs = encryptedSharedPreferences as EncryptedSharedPreferences
    val username = encryptedPrefs.getString(AppUtility.USER_NAME, "")
    val fullname = encryptedPrefs.getString(AppUtility.USER_FULL_NAME, "")
    val email = encryptedPrefs.getString(AppUtility.USER_EMAIL, "")
    val address = encryptedPrefs.getString(AppUtility.USER_ADDRESS, "")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(stringResource(R.string.user_detail), fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Divider()

        UserDetailItem(label = stringResource(R.string.name), value = "$fullname")
        UserDetailItem(label = stringResource(R.string.username), value = "@$username")
        UserDetailItem(label = stringResource(R.string.email), value = "$email")
        UserDetailItem(label = stringResource(R.string.address), value = "$address")

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onLogout()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = stringResource(R.string.logout), fontSize = 16.sp)
        }
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