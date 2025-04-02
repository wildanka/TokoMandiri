package com.example.tokomandiri.app.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tokomandiri.R
import com.example.tokomandiri.app.base.UiState
import com.example.tokomandiri.app.home.data.remote.response.ProductDto
import com.example.tokomandiri.app.home.presentation.component.ProductItem
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
                    Text("Home Screen LOADING")
                }
            }

            is UiState.Error -> {
                Box {
                    Text("Home Screen ERROR")
                }
            }
            is UiState.Success -> {
                HomeContent(uiState.data)
//                    orderReward = uiState.data,
//                    modifier = modifier,
//                    navigateToDetail = navigateToDetail
            }
        }
    }


}

@Composable
fun HomeContent(products: List<ProductDto>, modifier: Modifier = Modifier) {

    LazyColumn {
        items(products, key = { it.id }) { product ->
            ProductItem(
                productImageUrl = product.image.orEmpty(),
                title = product.title.orEmpty(),
                price = (product.price ?: 0.0) as Double,
                category = product.category.orEmpty(),
                rating = product.rating?.rate ?: 0.0,
                ratingCount = product.rating?.count ?: 0,
            )
        }
    }
}