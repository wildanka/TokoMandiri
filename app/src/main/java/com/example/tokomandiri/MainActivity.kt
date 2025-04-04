package com.example.tokomandiri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tokomandiri.app.TokoBerdiriApp
import com.example.tokomandiri.ui.theme.TokoMandiriTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


//        encryptedSharedPreferences.edit { putString("user_token", "your_token_here") }
//        encryptedSharedPreferences.edit { putString("user_token", "your_token_here") }
//
//        val token = encryptedSharedPreferences.getString("user_token", null)
// Retrieve data
        setContent {
            TokoMandiriTheme {
                TokoBerdiriApp(modifier = Modifier.padding())

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TokoMandiriTheme {
        Greeting("Android")
    }
}