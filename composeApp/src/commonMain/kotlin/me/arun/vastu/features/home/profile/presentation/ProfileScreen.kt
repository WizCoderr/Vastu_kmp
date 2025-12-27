package me.arun.vastu.features.home.profile.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import me.arun.vastu.core.components.icons.VastuIcons
import me.arun.vastu.core.components.icons.VastuIcons.Notifications
import me.arun.vastu.core.theme.DesignToken
import me.arun.vastu.core.theme.VastuMobileTheme
import me.arun.vastu.features.home.profile.components.InfoCard
import me.arun.vastu.features.home.profile.components.InfoRow
import me.arun.vastu.features.home.profile.components.SectionTitle
import me.arun.vastu.features.home.profile.components.SettingsItem
import org.koin.compose.viewmodel.koinViewModel

/**
 * Composable entry point for the Profile feature.
 */
@Composable
fun ProfileRoot(
    viewModel: ProfileViewModel = koinViewModel(),
    onEvent: (ProfileEvent) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            onEvent(event)
        }
    }

    ProfileScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

/**
 * A stateless composable that draws the UI for the Profile feature.
 */
@Composable
private fun ProfileScreen(
    state: ProfileState,
    onAction: (ProfileAction) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (!state.isAuthenticated) { // If not authenticated, show sign-in button
            OutlinedButton(
                onClick = { onAction(ProfileAction.OnSignInClick) },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Sign In")
            }
        } else { // If authenticated, show profile details
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(DesignToken.padding.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    shape = CircleShape,
                    tonalElevation = 2.dp,
                    modifier = Modifier.size(96.dp)
                ) {
                    Icon(
                        imageVector = VastuIcons.PersonFilled,
                        contentDescription = null,
                        modifier = Modifier.padding(24.dp)
                    )
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = state.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = state.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.height(24.dp))

                SectionTitle("Academic Information")

                InfoCard {
                    InfoRow("Institute", state.institute)
                    InfoRow("Student ID", state.studentId)
                    InfoRow("Joined", state.joinedDate)
                }

                Spacer(Modifier.height(24.dp))

                SectionTitle("Settings")

                InfoCard {
                    SettingsItem(
                        icon = VastuIcons.Notifications,
                        title = "Notifications",
                        onClick = { onAction(ProfileAction.OnNotificationsClick) }
                    )

                    SettingsItem(
                        icon = VastuIcons.Security,
                        title = "Privacy & Security",
                        onClick = {}
                    )

                    SettingsItem(
                        icon = Icons.Default.Description,
                        title = "Terms of Service",
                        onClick = {}
                    )

                    Spacer(Modifier.height(24.dp))

                    // Logout
                    OutlinedButton(
                        onClick = {
                            onAction(ProfileAction.OnLogoutClick)
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(VastuIcons.Logout, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Sign Out")
                    }

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "App Version ${state.appVersion}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

