package com.numaan.assesmentrngdev.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.numaan.assesmentrngdev.R
import com.numaan.assesmentrngdev.presentation.ui.theme.AssesmentRngDevTheme
import kotlinx.coroutines.delay

class LauncherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            LaunchScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LaunchScreen() {

    val context = LocalContext.current

    var isTextVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {

        delay(100)
        isTextVisible = true
        delay(1500)
        //Start a new activity
        val intent = Intent(
            context,
            MainActivity::class.java
        )
        context.startActivity(intent)

        //Optionally, finish the current activity to prevent going back to the launch screen
        (context as? Activity)?.finish()
    }

    AssesmentRngDevTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(color = colorResource(R.color.orange)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.top_1),
                        contentDescription = "Splash top image",
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.End),
                    )
                    AnimatedVisibility(
                        visible = isTextVisible,
                        enter = slideInVertically(
                            initialOffsetY = { -150 }, //Start from above
                            animationSpec = tween(durationMillis = 1000)
                        ) + fadeIn(tween(durationMillis = 1000)),
                        exit = fadeOut(tween(durationMillis = 500))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.app_name),
                                style = MaterialTheme.typography.displayLarge,
                                color = colorResource(R.color.white)
                            )
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = stringResource(id = R.string.splash_description),
                                style = MaterialTheme.typography.displaySmall,
                                color = colorResource(R.color.white)
                            )
                        }
                    }

                    Image(
                        painter = painterResource(id = R.drawable.bottom_1),
                        contentDescription = "Splash bottom image",
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Start),
                    )
                }
            })
    }
}



