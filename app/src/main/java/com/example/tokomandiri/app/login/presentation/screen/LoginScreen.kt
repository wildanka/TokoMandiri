package com.example.tokomandiri.app.login.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tokomandiri.R
import com.example.tokomandiri.app.login.presentation.component.MyTextField
import com.example.tokomandiri.ui.theme.TokoMandiriTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val username = remember {
        TextFieldState(initialText = "", initialSelection = TextRange(0))
    }
    val password = remember {
        TextFieldState(initialText = "", initialSelection = TextRange(0))
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceAround
        ) {

            Image(
                painter = painterResource(R.drawable.logo_market),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .fillMaxWidth(0.25f)
            )
            Spacer(modifier = Modifier.height(height = 16.dp))

            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp)
            ) {

                Text(
                    text = "Login ke Toko Berdiri",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(bottom = 30.dp)
                )
                MyTextField(
                    hint = "Username",
                    textFieldState = TextFieldState(),
                    leadingIcon = Icons.Outlined.Email,
                    trailingIcon = Icons.Outlined.Check,
                    isPassword = false,
                    onLeadingClick = {},
                    onTrailingClick = {},
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                MyTextField(
                    textFieldState = password,
                    leadingIcon = Icons.Outlined.Lock,
                    hint = "Password",
                    isPassword = true,
                    onLeadingClick = {},
                    onTrailingClick = {},
                    trailingText = "Forgot?",
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Button(
                    onClick = {},
                    modifier = Modifier.padding(top = 22.dp)
                ) {
                    Text(
                        "Login",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    TokoMandiriTheme { LoginScreen() }
}