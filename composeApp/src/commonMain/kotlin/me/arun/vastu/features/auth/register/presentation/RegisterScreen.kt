package me.arun.vastu.features.auth.register.presentation

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
import androidx.compose.material.icons.filled.Person
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
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import vastu.composeapp.generated.resources.Res
import vastu.composeapp.generated.resources.already_have_an_account
import vastu.composeapp.generated.resources.discipline_and_excellence
import vastu.composeapp.generated.resources.email
import vastu.composeapp.generated.resources.institute_of_learning
import vastu.composeapp.generated.resources.login_here
import vastu.composeapp.generated.resources.register
import vastu.composeapp.generated.resources.student_register
import vastu.composeapp.generated.resources.username

/**
 * Composable entry point for the Register feature.
 */
@Composable
fun RegisterRoot(
    viewModel: RegisterViewModel = koinViewModel(),
    onEvent: (RegisterEvent) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            onEvent(event)
        }
    }

    RegisterScreen(
        state = state,
        onAction = viewModel::onAction,
    )
}

/**
 * A stateless composable that draws the UI for the Register feature.
 */
@Composable
private fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
) {
    val colors = MaterialTheme.colorScheme
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
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
                        text = stringResource(Res.string.institute_of_learning),
                        color = colors.onBackground,
                        style = MaterialTheme.typography.titleMedium,
                    )

                    Text(
                        text = stringResource(Res.string.discipline_and_excellence),
                        color = colors.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall,
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = stringResource(Res.string.student_register),
                        color = colors.onBackground,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    RoundedInputField(
                        value = state.userName,
                        onValueChange = { onAction(RegisterAction.OnUserNameChanged(it)) },
                        placeholder = stringResource(Res.string.username),
                        leadingIcon = Icons.Default.Person,
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    RoundedInputField(
                        value = state.email,
                        onValueChange = { onAction(RegisterAction.OnEmailChanged(it)) },
                        placeholder = stringResource(Res.string.email),
                        leadingIcon = Icons.Default.Email,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Password
                    RoundedPasswordField(
                        value = state.password,
                        onValueChange = { onAction(RegisterAction.OnPasswordChanged(it)) },
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Confirm Password
                    RoundedPasswordField(
                        value = state.confirmPassword,
                        onValueChange = { onAction(RegisterAction.OnConfirmPasswordChanged(it)) },
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Login Button
                    Button(
                        onClick = { onAction(RegisterAction.OnRegisterClicked) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(28.dp),
                    ) {
                        Text(
                            text = stringResource(Res.string.register),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row {
                        Text(
                            text = stringResource(Res.string.already_have_an_account),
                            color = colors.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall,
                        )
                        Text(
                            text = stringResource(Res.string.login_here),
                            color = colors.primary,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.clickable {
                                onAction(RegisterAction.OnLoginClicked)
                            },
                        )
                    }
                }
            }
        }
    }
}
