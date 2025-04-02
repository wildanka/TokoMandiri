package com.example.tokomandiri.app.common.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tokomandiri.R

@Composable
fun ProductCounter(
    orderId: Int,
    orderCount: Int,
    onProductIncreased: (Int) -> Unit,
    onProductDecreased: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .size(width = 110.dp, height = 40.dp)
            .padding(4.dp)
    ) {
        CounterButton(
            text = stringResource(R.string.minus_symbol),
            modifier = Modifier
                .weight(1f)
                .size(30.dp)
                .padding(1.dp),
            onClick = {
                onProductDecreased(orderId)
            },
        )
        Text(
            text = orderCount.toString(),
            modifier = Modifier
                .testTag("count")
                .weight(1f),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        CounterButton(
            text = stringResource(R.string.plus_symbol),
            modifier = Modifier
                .weight(1f)
                .size(30.dp)
                .padding(1.dp),
            onClick = {
                onProductIncreased(orderId)
            },
        )
    }
}

@Composable
fun CounterButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    OutlinedIconButton(
        onClick = {
            onClick()
        },
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(size = 5.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}