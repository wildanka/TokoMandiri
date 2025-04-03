package com.example.tokomandiri.app.product.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tokomandiri.app.base.UiState
import com.example.tokomandiri.app.common.data.network.response.ProductDto
import com.example.tokomandiri.app.product.presentation.ui.home.component.ProductItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onShowProductDetail: (Int) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.fetchAllProducts()
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(128.dp)
                            .padding(16.dp)
                    )
                }
            }

            is UiState.Error -> {
                Box {
                    Text(uiState.errorMessage)
                }
            }

            is UiState.Success -> {
                HomeContent(
                    uiState.data,
                    onShowProductDetail = onShowProductDetail
                )
            }
        }
    }


}

@Composable
fun HomeContent(
    products: List<ProductDto>,
    modifier: Modifier = Modifier,
    onShowProductDetail: (Int) -> Unit,
) {
    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .background(color = Color.LightGray, shape = RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = "jewelery",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }
        }

        items(products, key = { it.id }) { product ->
            ProductItem(
                productImageUrl = product.image.orEmpty(),
                title = product.title.orEmpty(),
                price = (product.price ?: 0.0),
                category = product.category.orEmpty(),
                rating = product.rating?.rate ?: 0.0,
                ratingCount = product.rating?.count ?: 0,
                onItemClicked = { onShowProductDetail(product.id) }
            )
        }
    }
}