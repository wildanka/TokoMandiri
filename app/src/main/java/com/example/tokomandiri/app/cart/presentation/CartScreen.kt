package com.example.tokomandiri.app.cart.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tokomandiri.app.cart.presentation.component.CartItem
import com.example.tokomandiri.ui.theme.TokoMandiriTheme

@Composable
fun CartScreen(modifier: Modifier = Modifier) {
    CartContent()
}

@Composable
fun CartContent(modifier: Modifier = Modifier) {
    LazyColumn {
        item {
            CartItem(
                productId = 123,
                imageUrl = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                title = "Tolak Angin Madu",
                price = 109.95,
                qty = 0
            ){}
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CartContentPreview() {
    TokoMandiriTheme {
        CartContent()
    }
}