package com.example.tokomandiri.app.cart.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.tokomandiri.ui.theme.TokoMandiriTheme

@Composable
fun SummaryItem(
    productId: Int,
    imageUrl: String,
    title: String,
    price: Double,
    qty: Int,
    modifier: Modifier = Modifier,
    onItemClicked: () -> Unit,
) {
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable(onClick = onItemClicked),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = modifier
                    .height(84.dp)
                    .width(64.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.ExtraLight
                )
                Text(text = "$$price x $qty", fontSize = 14.sp)
                Text(
                    text = "$${price*qty}", fontSize = 14.sp,
                    modifier = Modifier.align(alignment = Alignment.Start)
                )
            }
        }

    }
}


@Preview
@Composable
private fun CartItemPreview() {
    TokoMandiriTheme {
        SummaryItem(
            productId = 123,
            imageUrl = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            title = "Tolak Angin Madu",
            price = 109.95,
            qty = 0,
            onItemClicked = {},
        )
    }
}