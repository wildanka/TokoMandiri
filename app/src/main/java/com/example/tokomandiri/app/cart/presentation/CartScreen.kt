package com.example.tokomandiri.app.cart.presentation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.tokomandiri.app.cart.presentation.component.CartItem
import com.example.tokomandiri.ui.theme.TokoMandiriTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(modifier: Modifier = Modifier, viewModel: CartViewModel = koinViewModel()) {
    val pagedCartItems = viewModel.pagedCartItems.collectAsLazyPagingItems()

    Log.d("WLDN", "CartScreen: ")
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        Log.d("WLDN", "CartScreen LoadState.Success: ${pagedCartItems.itemCount}")
        items(pagedCartItems.itemCount) { index ->
            val userCartEntity = pagedCartItems[index]
            userCartEntity?.let {
                CartItem(
                    productId = userCartEntity.productId,
                    imageUrl = userCartEntity.image,
                    title = userCartEntity.title,
                    price = userCartEntity.price,
                    qty = userCartEntity.qty,
                    onItemClicked = {

                    },
                    onProductCountChanged = { productId, newQty ->
                        if(newQty > 0){
                            viewModel.insertProductToCart(userCartEntity.copy(qty = newQty))
                        }else{
                            viewModel.removeProductFromCart(userCartEntity)
                        }
                    },
                )
            }
        }

        // Show loading state at the bottom
        pagedCartItems.apply {
            when {
                loadState.append is LoadState.Loading -> {
                    Log.d("WLDN", "CartScreen LoadState.Loading: ")

                    item { CircularProgressIndicator(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)) }
                }

                loadState.append is LoadState.Error -> {
                    Log.d("WLDN", "CartScreen LoadState.Error: ")
                    item {
                        val error = (loadState.append as LoadState.Error).error
                        Text("Error: ${error.message}", color = Color.Red)
                    }
                }
            }
        }
    }

}

@Composable
fun CartContent(modifier: Modifier = Modifier) {
//    LazyColumn {
//        item {
//            CartItem(
//                productId = 123,
//                imageUrl = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
//                title = "Tolak Angin Madu",
//                price = 109.95,
//                qty = 0
//            ){}
//        }
//    }
}

@Preview(showBackground = true)
@Composable
private fun CartContentPreview() {
    TokoMandiriTheme {
        CartContent()
    }
}