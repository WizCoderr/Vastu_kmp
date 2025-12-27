package me.arun.vastu.features.auth.landing.presentation.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import vastu.composeapp.generated.resources.Res
import vastu.composeapp.generated.resources.arun
import vastu.composeapp.generated.resources.vastu

@Composable
fun LandingPagerItem(page: Int) {
    val imageRes = when (page) {
        0 -> Res.drawable.vastu
        1 -> Res.drawable.arun
        2 -> Res.drawable.vastu
        else -> Res.drawable.arun
    }

    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
