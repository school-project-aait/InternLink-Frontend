package com.site7x24learn.internshipfrontend.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AnimatedDot(offset: Float) {
    Box(
        modifier = Modifier
            .offset(y = (-offset).dp)
            .size(12.dp)
            .clip(MaterialTheme.shapes.small)
            .background(Color.Blue)
    )
}