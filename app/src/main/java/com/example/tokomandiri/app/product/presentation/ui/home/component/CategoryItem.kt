package com.example.tokomandiri.app.product.presentation.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tokomandiri.R
import com.example.tokomandiri.ui.theme.TokoMandiriTheme

@Composable
fun CategoryItem(
    label: String,
    painterDrawable: Int?,
    isActive: Boolean = false,
    modifier: Modifier = Modifier,
    onCategoryClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(
                color = if (isActive) {
                    Color.Black
                } else {
                    Color.White
                },
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(
                onClick = { onCategoryClick() }
            )
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = if(isActive){
                FontWeight.Medium
            }else{
                FontWeight.Light
            },
            modifier = Modifier
                .padding(vertical = 4.dp),
            color = if (isActive) {
                Color.White
            } else {
                Color.Black
            }
        )
        if(painterDrawable != null){
            Icon(
                painter = painterResource(painterDrawable),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .padding(start = 4.dp),
                tint = if (isActive) {
                    Color.White
                } else {
                    Color.Black
                }
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryItemPreview() {
    TokoMandiriTheme {
        CategoryItem(
            label = "Jewelery",
            painterDrawable = R.drawable.ic_jewellery,
            isActive = true,
            onCategoryClick = { },
        )
    }
}