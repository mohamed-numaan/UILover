package com.numaan.assesmentrngdev.presentation.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.numaan.assesmentrngdev.R
import com.numaan.assesmentrngdev.utils.PreferencesManager


@Composable
fun HomeScreen(onLogout: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.cultured))
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        ProfileCard(onLogout)
        Spacer(modifier = Modifier.height(16.dp))
        OfferView()
        MenuTable()
    }
}

@Composable
fun MenuTable() {
    val menuItems = listOf(
        R.drawable.ic_1 to "Inbox",
        R.drawable.ic_2 to "Maps",
        R.drawable.ic_3 to "Chat",
        R.drawable.ic_4 to "Report",
        R.drawable.ic_5 to "Calendar",
        R.drawable.ic_6 to "Tips",
        R.drawable.ic_7 to "Settings",
        R.drawable.ic_8 to "Share",
        R.drawable.ic_9 to "More"
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // 3 columns for grid
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp) // Padding for grid
    ) {
        items(menuItems) { item ->
            MenuItem(icon = item)
        }
    }
}

@Composable
fun MenuItem(icon: Pair<Int, String>) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .height(100.dp)
            .clickable { /* Handle item click */ }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = icon.first),
                contentDescription = icon.second,
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = icon.second,
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TableRow(icons: List<Pair<Int, String>>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        icons.forEach { icon ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .background(Color.White, shape = RoundedCornerShape(25.dp))
                    .height(100.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(icon.first),
                        contentDescription = "first",
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = icon.second,
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}

@Composable
fun ProfileCard(onLogout: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier.height(292.dp)
    ) {
        val (boxGrey, image, morning, name, favButton, profileButton) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(boxGrey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .height(260.dp)
                .padding(top = 30.dp)
                .padding(horizontal = 16.dp)
                .background(
                    colorResource(R.color.grey),
                    shape = RoundedCornerShape(25.dp)
                )
        ) { }
        Text(
            text = "Good Morning",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp)
                .constrainAs(morning) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(start = 32.dp)
                .width(133.dp)
                .height(185.dp)
                .clip(RoundedCornerShape(25.dp))
                .constrainAs(image) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(boxGrey.start)
                }
        )
        Text(
            text = "Sara \nAnderson",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(name) {
                    start.linkTo(image.end)
                    top.linkTo(image.top)
                }
        )
        Image(
            painter = painterResource(R.drawable.profile_btn),
            contentDescription = "user",
            modifier = Modifier
                .clickable {
                    PreferencesManager.setLoggedIn(isLoggedIn = false)
                    onLogout()
                }
                .padding(end = 48.dp)
                .constrainAs(profileButton) {
                    end.linkTo(boxGrey.end)
                    top.linkTo(boxGrey.bottom)
                    bottom.linkTo(boxGrey.bottom)
                }

        )
        Image(
            painter = painterResource(R.drawable.fav),
            contentDescription = "favourite",
            modifier = Modifier
                .constrainAs(favButton) {
                    end.linkTo(profileButton.start)
                    top.linkTo(boxGrey.bottom)
                    bottom.linkTo(boxGrey.bottom)
                    start.linkTo(image.end)
                }
        )

    }
}

@Composable
fun OfferView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(146.dp)
            .padding(16.dp)
            .background(colorResource(R.color.yellow), shape = RoundedCornerShape(20.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "until 20 june - 30 July",
                    style = TextStyle(color = Color.Black)
                )
                Text(
                    text = "30%",
                    color = colorResource(R.color.blue),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.discount),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.logo_banner),
                contentDescription = "logo",
                modifier = Modifier.size(100.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    // Provide a mock `onLoginSuccess` callback, since it is required by the composable
    HomeScreen(onLogout = {
        // Just a mock success callback for preview
        println("Logout successful")
    })
}