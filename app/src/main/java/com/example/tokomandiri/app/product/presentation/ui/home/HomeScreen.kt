package com.example.tokomandiri.app.product.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tokomandiri.app.base.UiState
import com.example.tokomandiri.app.common.data.network.response.ProductDto
import com.example.tokomandiri.app.product.presentation.ProductUtil
import com.example.tokomandiri.app.product.presentation.ui.home.component.CategoryItem
import com.example.tokomandiri.app.product.presentation.ui.home.component.ProductItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onShowProductDetail: (Int) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    var activeCategory = remember { mutableStateOf("All") }

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllProduct()
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
                val uniqueCategories = listOf("All") + uiState.data.mapNotNull { it.category }.distinct()

                val filteredData = uiState.data.filter { product ->
                    activeCategory.value == "All" || product.category == activeCategory.value
                }
                HomeContent(
                    filteredData,
                    uniqueCategories,
                    activeCategory.value,
                    onShowProductDetail = onShowProductDetail,
                    onCategoryClick = { newActiveCategory ->
                        activeCategory.value = newActiveCategory
                    }
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    products: List<ProductDto>,
    categories: List<String>,
    activeCategory: String,
    modifier: Modifier = Modifier,
    onShowProductDetail: (Int) -> Unit,
    onCategoryClick: (String) -> Unit
) {
    LazyColumn {
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = modifier
            ) {
                items(categories) {
                    CategoryItem(
                        label = it,
                        painterDrawable = ProductUtil.getCategoryIcon(it),
                        onCategoryClick = {
                            onCategoryClick(it)
                        },
                        isActive = activeCategory == it,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
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