package com.example.tokomandiri.app.product.presentation.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tokomandiri.ui.theme.TokoMandiriTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailScreen(productId: Int, modifier: Modifier = Modifier, viewModel: DetailViewModel = koinViewModel()) {
    Column {
    }
}


@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    TokoMandiriTheme {
        DetailScreen(1)
    }
}