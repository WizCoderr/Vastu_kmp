package me.arun.vastu.features.auth.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import me.arun.vastu.features.auth.component.RoundedInputField
import me.arun.vastu.features.auth.component.RoundedPasswordField
import org.koin.compose.viewmodel.koinViewModel

/**
 * Composable entry point for the Login feature.
 */
@Composable
fun LoginRoot(
    viewModel: LoginViewModel = koinViewModel(),
    onEvent: (LoginEvent) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            onEvent(event)
        }
    }

    LoginScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

/**
 * A stateless composable that draws the UI for the Login feature.
 */
@Composable
private fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {
    val colors = MaterialTheme.colorScheme
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.background)
                    .padding(horizontal = 24.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // Logo container
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = colors.surfaceVariant,
                        tonalElevation = 2.dp,
                        modifier = Modifier.size(72.dp),
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = null,
                                tint = colors.primary,
                                modifier = Modifier.size(36.dp),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "INSTITUTE OF LEARNING",
                        color = colors.onBackground,
                        style = MaterialTheme.typography.titleMedium,
                    )

                    Text(
                        text = "Discipline & Excellence",
                        color = colors.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall,
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "Student Login",
                        color = colors.onBackground,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    RoundedInputField(
                        value = state.email,
                        onValueChange = { onAction(LoginAction.OnEmailChanged(it)) },
                        placeholder = "Email",
                        leadingIcon = Icons.Default.Email,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Password
                    RoundedPasswordField(
                        value = state.password,
                        onValueChange = { onAction(LoginAction.OnPasswordChanged(it)) },
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Login Button
                    Button(
                        onClick = { onAction(LoginAction.OnLoginClicked) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(28.dp),
                    ) {
                        Text(
                            text = "Login",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row {
                        Text(
                            text = "Don't have an account? ",
                            color = colors.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall,
                        )
                        Text(
                            text = "Register here",
                            color = colors.primary,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.clickable { onAction(LoginAction.OnRegisterClicked) },
                        )
                    }
                }
            }
        }
    }
}
