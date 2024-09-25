package com.brosedda.mymediary.ui.components.userActivity

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.brosedda.mymediary.R

@Composable
fun UserActivityTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    @StringRes labelText: Int,
    imeAction: ImeAction,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    noBottomMargin: Boolean = false,
    onFocusLost: (FocusState) -> Unit = { }
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        enabled = enabled,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        label = {
            Text(
                text = stringResource(labelText),
                style = MaterialTheme.typography.labelSmall
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        trailingIcon = trailingIcon,
        isError = isError,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = if (noBottomMargin) 0.dp else dimensionResource(R.dimen.padding_medium))
            .onFocusChanged { onFocusLost(it) }
    )
}

@Composable
fun UserActivityPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    @StringRes labelText: Int,
    imeAction: ImeAction,
    isVisible: Boolean,
    toggleVisibility: () -> Unit,
    modifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    enabled: Boolean = true,
    noBottomMargin: Boolean = false,
    onFocusLost: (FocusState) -> Unit = {}
) {
    UserActivityTextField(
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        labelText = labelText,
        imeAction = imeAction,
        onFocusLost = onFocusLost,
        visualTransformation = when (isVisible) {
            true -> VisualTransformation.None
            false -> PasswordVisualTransformation()
        },
        enabled = enabled,
        trailingIcon = {
            val (icon, description) = when (isVisible) {
                true -> Pair(
                    Icons.Filled.VisibilityOff,
                    stringResource(R.string.hide_password)
                )

                false -> Pair(
                    Icons.Filled.Visibility,
                    stringResource(R.string.show_password)
                )
            }

            IconButton(
                onClick = toggleVisibility
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = description
                )
            }
        },
        keyboardActions = keyboardActions,
        noBottomMargin = noBottomMargin,
        modifier = modifier
    )
}