package me.arun.vastu.features.auth.landing.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.arun.vastu.core.theme.VastuMobileTheme
import me.arun.vastu.features.auth.landing.presentation.componets.LandingPagerItem
import me.arun.vastu.features.auth.landing.presentation.componets.OutlineButton
import me.arun.vastu.features.auth.landing.presentation.componets.PagerIndicator
import me.arun.vastu.features.auth.landing.presentation.componets.PrimaryButton
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

/**
 * Composable entry point for the Landing feature.
 */
@Composable
fun LandingRoot(
    viewModel: LandingViewModel = koinViewModel(),
    onEvent: (LandingEvent) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.event.collect { event ->
            onEvent(event)
        }
    }

    LandingScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

/**
 * A stateless composable that draws the UI for the Landing feature.
 */
@Composable
private fun LandingScreen(
    state: LandingState,
    onAction: (LandingAction) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = state.currentPage,
        pageCount = { state.totalPages }
    )

    // Sync pager scroll → ViewModel state
    LaunchedEffect(pagerState.currentPage) {
        onAction(LandingAction.OnPageChanged(pagerState.currentPage))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF0B1220), Color(0xFF060B14))
                )
            )
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(48.dp))

            // ✅ CAROUSEL
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
            ) { page ->
                LandingPagerItem(page)
            }

            Spacer(Modifier.height(20.dp))

            PagerIndicator(
                totalDots = state.totalPages,
                selectedIndex = state.currentPage
            )

            Spacer(Modifier.height(24.dp))

            Text(
                "Master Your Discipline",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(12.dp))

            Text(
                "Long-term learning for serious students\nfocused on growth.",
                color = Color(0xFF9AA4B2),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.weight(1f))

            PrimaryButton(
                text = "Log In",
                onClick = { onAction(LandingAction.OnLoginClick) }
            )

            Spacer(Modifier.height(12.dp))

            OutlineButton(
                text = "Create Account",
                onClick = { onAction(LandingAction.OnCreateAccountClick) }
            )

            Spacer(Modifier.height(20.dp))

            Text(
                "Explore Courses",
                color = Color(0xFF7C8AA5),
                modifier = Modifier.clickable {
                    onAction(LandingAction.OnExploreClick)
                }
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}
@Preview
@Composable
fun PreviewLanding(
    modifier: Modifier = Modifier,
){
    VastuMobileTheme {
        LandingScreen(
            state = LandingState(
                currentPage = 0,

            ),
            onAction={}
        )
    }
}