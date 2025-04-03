package com.example.tokomandiri.app.cart.presentation.summary

import androidx.compose.foundation.Canvas
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.tokomandiri.R
import com.example.tokomandiri.app.cart.presentation.component.SummaryItem
import com.example.tokomandiri.ui.theme.TokoMandiriTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SummaryScreen(modifier: Modifier = Modifier, viewModel: SummaryViewModel = koinViewModel()) {
    val pagedCartItems = viewModel.pagedCartItems.collectAsLazyPagingItems()
    var totalPrice by remember { mutableStateOf(0.0) }

    // Calculate totalPrice whenever the list of items changes
    LaunchedEffect(pagedCartItems.itemCount) {
        // Reset total price on any change
        totalPrice = 0.0

        // Calculate total price by iterating through items
        pagedCartItems.itemSnapshotList.forEach { cartItem ->
            cartItem?.let {
                totalPrice += it.price * it.qty
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        items(pagedCartItems.itemCount) { index ->
            val userCartEntity = pagedCartItems[index]
            userCartEntity?.let {
                SummaryItem(
                    productId = userCartEntity.productId,
                    imageUrl = userCartEntity.image,
                    title = userCartEntity.title,
                    price = userCartEntity.price,
                    qty = userCartEntity.qty,
                    onItemClicked = {}
                )
            }
        }

        item{
            Spacer(Modifier.height(16.dp))
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(30f, 10f), 10f)
            Canvas(Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .height(1.dp)) {
                drawLine(
                    color = Color.DarkGray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    pathEffect = pathEffect
                )
            }
        }
        
        if (pagedCartItems.itemCount > 0) {
            item {
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Total Price: $${"%.2f".format(totalPrice)}",
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 8.dp)
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Text(text = stringResource(R.string.pay_now), fontSize = 16.sp)
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

@Preview(showBackground = true)
@Composable
private fun SummaryScreenPreview() {
    TokoMandiriTheme {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        Canvas(Modifier
            .fillMaxWidth()
            .height(1.dp)) {
            drawLine(
                color = Color.DarkGray,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                pathEffect = pathEffect
            )
        }
    }
    
}