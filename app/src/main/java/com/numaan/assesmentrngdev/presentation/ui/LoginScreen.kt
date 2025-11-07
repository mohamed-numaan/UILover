package com.numaan.assesmentrngdev.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.numaan.assesmentrngdev.R
import com.numaan.assesmentrngdev.presentation.ui.theme.AssesmentRngDevTheme
import com.numaan.assesmentrngdev.utils.PreferencesManager
import com.numaan.assesmentrngdev.presentation.vm.LoginViewModel
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(), onLoginSuccess: () -> Unit) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val loginStatus by viewModel.loginStatus.observeAsState()

    fun showSnackBarMessage(message: String) {
        coroutineScope.launch {
            snackBarHostState.showSnackbar(message)
        }
    }

    LaunchedEffect(loginStatus) {
        when (loginStatus) {
            null -> {
                //Skip if null
            }

            true -> {
                PreferencesManager.setLoggedIn(isLoggedIn = true)
                onLoginSuccess()
            }

            false -> {

                showSnackBarMessage(context.getString(R.string.error_invalid_credential))
                viewModel.resetFlag()
            }
        }
    }
    AssesmentRngDevTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = { SnackbarHost((snackBarHostState)) },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(Color.White)
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.top_2),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.displayLarge,
                        color = colorResource(id = R.color.orange),
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .wrapContentWidth(),
                    )
                    Text(
                        text = stringResource(R.string.login_description),
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .wrapContentWidth(),
                    )
                    Text(
                        text = stringResource(R.string.please_login),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .wrapContentWidth(),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it.trim() },
                        label = { Text(stringResource(R.string.email)) },
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                            .fillMaxWidth(),
                        textStyle = TextStyle(
                            color = Color.Black
                        ),
                        shape = MaterialTheme.shapes.medium,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it.trim() },
                        visualTransformation = PasswordVisualTransformation(),
                        label = { Text(stringResource(R.string.password)) },
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                            .fillMaxWidth(),
                        textStyle = TextStyle(
                            color = Color.Black
                        ),
                        shape = MaterialTheme.shapes.medium,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        )
                    )
                    Button(
                        onClick = {
                            focusManager.clearFocus()
                            viewModel.login(username, password)
                        },
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 32.dp)
                            .fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.blue))
                    ) {
                        Text(
                            text = stringResource(R.string.login),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                    Text(
                        text = stringResource(R.string.forgot_password),
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black,
                        modifier = Modifier
                            .clickable {
                                showSnackBarMessage(context.getString(R.string.error_not_implement))
                            }
                            .padding(top = 16.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 32.dp)
                            .fillMaxWidth(),
                    ) {
                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = colorResource(R.color.grey)
                        )
                        Text(
                            text = stringResource(R.string.login_with),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                        )
                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = colorResource(R.color.grey)
                        )
                    }
                    Button(
                        onClick = { showSnackBarMessage(context.getString(R.string.error_not_implement)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                            .border(2.dp, Color.Blue, MaterialTheme.shapes.medium),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.white)),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = stringResource(R.string.google),
                            style = MaterialTheme.typography.titleMedium,
                            color = colorResource(R.color.blue),
                            modifier = Modifier
                                .padding(5.dp)
                        )
                    }
                    Text(
                        text = stringResource(R.string.no_account),
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(id = R.string.signup),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.blue),
                        modifier = Modifier
                            .clickable {
                                showSnackBarMessage(context.getString(R.string.error_not_implement))
                            }
                            .padding(top = 16.dp, bottom = 16.dp)
                            .wrapContentSize(),
                        textAlign = TextAlign.Center
                    )
                }
            })
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(onLoginSuccess = {
        println("Login Successful")
    })
}
