package com.example.tokomandiri.app.product.presentation.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.tokomandiri.R
import com.example.tokomandiri.app.base.UiState
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import com.example.tokomandiri.app.common.data.network.response.Rating
import com.example.tokomandiri.app.common.presentation.component.ProductCounter
import com.example.tokomandiri.ui.theme.TokoMandiriTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = koinViewModel()
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.fetchProduct(productId)
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("Home Screen LOADING")
                }
            }

            is UiState.Error -> {
                Box {
                    Text("Home Screen ERROR")
                }
            }

            is UiState.Success -> {
                DetailContent(
                    productData = uiState.data,
                    modifier = modifier
                ) { qty ->
                    viewModel.addProductToCart(uiState.data, qty)
                }
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailContent(
    productData: UserCartEntity,
    modifier: Modifier = Modifier,
    onAddToCard: (Int) -> Unit
) {

    var cartCount = remember { mutableIntStateOf(productData.qty) }

    Column(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            item {
                Column(modifier = modifier.padding(16.dp)) {
                    AsyncImage(
                        model = productData.image,
                        contentDescription = null,
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Text(
                        text = productData.title.orEmpty(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = modifier.padding(top = 16.dp)
                    )
                    Text(text = "$${productData.price}")
                    Box(
                        modifier = Modifier
                            .background(color = Color.LightGray, shape = RoundedCornerShape(4.dp))
                    ) {
                        Text(
                            text = productData.category.toString(),
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
                        Text(text = "${productData.rating?.rate} (${productData.rating?.count})")
                    }
                    Text(
                        text = productData.description.orEmpty(),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }
        }
        Column {
            ProductCounter(
                orderId = productData.productId,
                orderCount = cartCount.intValue,
                onProductIncreased = { cartCount.intValue = cartCount.intValue + 1 },
                onProductDecreased = {
                    if (cartCount.intValue > 0) {
                        cartCount.intValue = cartCount.intValue - 1
                    }
                },
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
            Button(
                onClick = { onAddToCard(cartCount.intValue) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = stringResource(R.string.add_to_cart))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    TokoMandiriTheme {
        DetailContent(
            productData = UserCartEntity(
                title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                price = 109.95,
                description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                category = "men's clothing",
                productId = 0,
                image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                rating = Rating(),
            )
        ) {

        }
    }
}