package com.mahad.myapplicationcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image as Image1
import androidx.compose.foundation.layout.Column as Column1
import androidx.compose.ui.res.painterResource as painterResource1

//@Preview(showBackground = true, widthDp = 400)
@Composable
private fun Offer_Preview()
{
    Offers("Title", "Description")
}


@Composable
fun Offers(title: String, description: String = "") {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        Image1(painter = painterResource1(id = R.drawable.ic_launcher_background),
            contentDescription = "Background Pattern",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.matchParentSize()
        )
        Column1(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.Cyan)
                    .padding(16.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.Cyan)
                    .padding(16.dp)
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun OffersPage() {
    Column1(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        Offers(title = "Early Coffee", description = "10% off. Offer valid from 6am to 9am.")
        Offers(title = "Welcome Gift", description = "25% off on your first order.")
    }
}