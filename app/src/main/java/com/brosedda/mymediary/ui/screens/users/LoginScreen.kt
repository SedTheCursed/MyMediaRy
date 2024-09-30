package com.brosedda.mymediary.ui.screens.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.model.User
import com.brosedda.mymediary.ui.components.userActivity.UserActivityPasswordField
import com.brosedda.mymediary.ui.theme.MyMediaRyTheme
import com.brosedda.mymediary.ui.viewModel.users.LoginViewModel

private fun login(
    viewModel: LoginViewModel,
    connect: () -> Unit,
    password: String,
) {
    viewModel.checkPassword(password)
    if (!viewModel.isWrong) connect()
}

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
        UserActivityPasswordField(
            value = viewModel.input,
            onValueChange = { viewModel.updateInput(it) },
            isError = viewModel.isWrong,
            labelText = when (viewModel.isWrong) {
                true -> R.string.incorrect_password
                false -> R.string.password
            },
            imeAction = ImeAction.Done,
            isVisible = viewModel.isVisible,
            toggleVisibility = { viewModel.toggleVisibility() },
            keyboardActions = KeyboardActions(
                onDone = { login(viewModel, connect, password) }
            ),
            noBottomMargin = false
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
