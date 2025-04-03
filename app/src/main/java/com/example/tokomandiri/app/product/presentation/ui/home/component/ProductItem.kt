package com.example.tokomandiri.app.product.presentation.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.tokomandiri.ui.theme.TokoMandiriTheme

@Composable
fun ProductItem(
    productImageUrl: String?,
    title: String,
    price: Double,
    category: String,
    rating: Double,
    ratingCount: Int,
    modifier: Modifier = Modifier,
    onItemClicked: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable(onClick = onItemClicked),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = modifier.padding(16.dp)) {
            AsyncImage(
                model = productImageUrl,
                modifier = Modifier
                    .height(84.dp)
                    .width(64.dp),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = "$$price", fontSize = 14.sp,)
                Box(
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = RoundedCornerShape(4.dp))
                ) {
                    Text(
                        text = category,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                }
                Row {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                    Text(text = "$rating ($ratingCount)", fontSize = 14.sp,)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    TokoMandiriTheme {
        ProductItem(
            productImageUrl = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            title = "Tolak Angin Madu",
            price = 109.95,
            category = "men's clothing",
            rating = 3.9,
            ratingCount = 120,
            onItemClicked = {},
        )
    }
}