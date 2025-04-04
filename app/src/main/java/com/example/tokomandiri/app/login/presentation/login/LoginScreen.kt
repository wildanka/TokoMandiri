package com.example.tokomandiri.app.login.presentation.login

import android.content.SharedPreferences
import android.widget.Toast
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.security.crypto.EncryptedSharedPreferences
import com.example.tokomandiri.R
import com.example.tokomandiri.app.login.domain.model.LoginUiState
import com.example.tokomandiri.app.login.presentation.component.MyTextField
import com.example.tokomandiri.framework.AppUtility
import com.example.tokomandiri.ui.theme.TokoMandiriTheme
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.GlobalContext


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val encryptedSharedPreferences: SharedPreferences = GlobalContext.get().get()
    val encryptedPrefs = encryptedSharedPreferences as EncryptedSharedPreferences

    val username = remember {
        TextFieldState(initialText = "", initialSelection = TextRange(0))
    }
    val password = remember {
        TextFieldState(initialText = "", initialSelection = TextRange(0))
    }


    val loginState by viewModel.loginState.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.loginEvent.collect { user ->
            val editor = encryptedPrefs.edit()
            // Put values (you can also put other types like booleans, ints, etc.)
//                        editor.putString("username", username.text)
//                        editor.putString("password", password.text)
            editor.putString(AppUtility.APP_TOKEN, "TOKEN 123")

            // Commit or apply the changes
            editor.apply()

            onLoginSuccess()
        }
    }


    val context = LocalContext
    val snackbarHostState = remember { SnackbarHostState() }
    val errorEvent by viewModel.errorMessage.collectAsState()
    LaunchedEffect(errorEvent) {
        errorEvent?.getContentIfNotHandled()?.let { message ->
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            snackbarHostState.showSnackbar(message)
        }

    }


    when (loginState) {
        is LoginUiState.Loading -> {
            // Show loading
        }

        is LoginUiState.Error -> {
            val message = (loginState as LoginUiState.Error).message
            Text("Login failed: $message")
        }

        LoginUiState.Success -> {
            // Already handled by event
        }

        LoginUiState.Idle -> {
            // Initial state
        }
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
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
                    textFieldState = username,
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
                    onClick = {
                        //hit the login API
                        viewModel.login(username.text.toString(), password.text.toString())

//                        onLoginSuccess()
                    },
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
    TokoMandiriTheme { LoginScreen(onLoginSuccess = {}) }
}