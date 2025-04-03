package com.example.tokomandiri.app.cart.presentation.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.tokomandiri.app.cart.presentation.component.CartItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(modifier: Modifier = Modifier, onCheckoutClick: () -> Unit, viewModel: CartViewModel = koinViewModel()) {
    val pagedCartItems = viewModel.pagedCartItems.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
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
                        if (newQty > 0) {
                            viewModel.insertProductToCart(userCartEntity.copy(qty = newQty))
                        } else {
                            viewModel.removeProductFromCart(userCartEntity)
                        }
                    },
                )
            }
        }

        if (pagedCartItems.itemCount > 0) {
            item{
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = { onCheckoutClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Text(text = "Checkout", fontSize = 18.sp)
                }
                Spacer(Modifier.height(56.dp))
            }
        }

        // Show loading state at the bottom
        pagedCartItems.apply {
            when {
                loadState.append is LoadState.Loading -> {

                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    item {
                        val error = (loadState.append as LoadState.Error).error
                        Text("Error: ${error.message}", color = Color.Red)
                    }
                }
            }
        }
    }
}