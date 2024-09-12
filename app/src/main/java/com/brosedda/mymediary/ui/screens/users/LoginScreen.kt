package com.brosedda.mymediary.ui.screens.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.model.User
import com.brosedda.mymediary.ui.theme.MyMediaRyTheme
import com.brosedda.mymediary.ui.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    user: User,
    connect: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel()
) {
    if (user.password == null) return
    val password = user.password

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        TextField(
            value = viewModel.input,
            onValueChange = { viewModel.updateInput(it) },
            visualTransformation = when (viewModel.isVisible) {
                true -> VisualTransformation.None
                false -> PasswordVisualTransformation()
            },
            singleLine = true,
            shape = shapes.large,
            label = {
                Text(
                    text = stringResource(
                        when (viewModel.isWrong) {
                            true -> R.string.incorrect_password
                            false -> R.string.password
                        }
                    )
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                val (icon, description) = when (viewModel.isVisible) {
                    true -> Pair(
                        Icons.Filled.VisibilityOff,
                        stringResource(R.string.show_password)
                    )
                    false -> Pair(
                        Icons.Filled.Visibility,
                        stringResource(R.string.hide_password)
                    )
                }

                IconButton(
                    onClick = { viewModel.toggleVisibility() }
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = description
                    )
                }
            },
            isError = viewModel.isWrong,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { login(viewModel, connect, password) }
            ),
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_medium))
        )

        Button(
            onClick = { login(viewModel, connect, password) },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.primaryContainer,
                contentColor = colorScheme.onPrimaryContainer
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = null,
                )

                Spacer(
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )

                Text(
                    text = stringResource(R.string.login),
                    style = typography.labelSmall
                )
            }
        }
    }
}

private fun login(
    viewModel: LoginViewModel,
    connect: () -> Unit,
    password: String,
) {
    viewModel.checkPassword(password)
    if (!viewModel.isWrong) connect()
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MyMediaRyTheme(darkTheme = false) {
        LoginScreen(
            User("", ""),
            {}
        )
    }
}
