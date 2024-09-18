package com.brosedda.mymediary.ui.screens.users

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.DataSource
import com.brosedda.mymediary.data.model.User
import com.brosedda.mymediary.ui.components.userActivity.ProfileItem
import com.brosedda.mymediary.ui.components.userActivity.UserActivityPasswordField
import com.brosedda.mymediary.ui.components.userActivity.UserActivityTextField
import com.brosedda.mymediary.ui.theme.MyMediaRyTheme
import com.brosedda.mymediary.ui.viewModel.CreateProfileViewModel

@Composable
fun CreationScreen(
    users: List<String>,
    @DrawableRes avatar: Int,
    chooseAvatar: () -> Unit,
    addProfile: (String, String?) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateProfileViewModel = viewModel(),
) {
    viewModel.let { vm ->
        val focusManager = LocalFocusManager.current

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            UserActivityTextField(
                value = vm.name,
                onValueChange = { vm.updateName(it, users) },
                isError = vm.isNameUsed || vm.isNameEmpty,
                labelText = when {
                    vm.isNameUsed -> R.string.used_name
                    vm.isNameEmpty -> R.string.mandatory_name
                    else -> R.string.profile_name
                },
                imeAction = when (vm.withPassword) {
                    true -> ImeAction.Next
                    false -> ImeAction.Done
                }
            )

            UserActivityPasswordField(
                value = vm.password,
                onValueChange = { vm.updatePassword(it) },
                isError = !vm.doesMatch,
                labelText = when (vm.doesMatch) {
                    true -> R.string.password
                    false -> R.string.not_matching_password
                },
                imeAction = ImeAction.Next,
                isVisible = vm.isPasswordVisible,
                enabled = vm.withPassword,
                toggleVisibility = { vm.toggleVisibility() },
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            UserActivityPasswordField(
                value = vm.confirmation,
                onValueChange = { vm.updateConfirmation(it) },
                imeAction = ImeAction.Done,
                isError = !vm.doesMatch,
                isVisible = vm.isPasswordVisible,
                enabled = vm.withPassword,
                labelText = when (vm.doesMatch) {
                    true -> R.string.password_confirmation
                    false -> R.string.not_matching_password
                },
                toggleVisibility = { vm.toggleVisibility() },
                keyboardActions = KeyboardActions(
                    onDone = {
                        vm.activateCheckPassword()
                        vm.checkPassword()
                    }
                ),
                noBottomMargin = true
            )

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Checkbox(
                    checked = !vm.withPassword,
                    onCheckedChange = { vm.togglePassword() },
                    colors = CheckboxDefaults.colors().copy(
                        uncheckedCheckmarkColor = Color.Transparent,
                        uncheckedBoxColor = colorScheme.surface,
                        uncheckedBorderColor = colorScheme.onSurface,
                        checkedCheckmarkColor = colorScheme.onSurface,
                        checkedBoxColor = colorScheme.surface,
                        checkedBorderColor = colorScheme.onSurface
                    )
                )

                Text(
                    text = stringResource(R.string.no_password),
                    style = typography.labelSmall,
                    modifier = Modifier
                        .clickable { vm.togglePassword() }
                )
            }

            ProfileItem(
                user = User(
                    name = stringResource(R.string.choose_avatar),
                    avatar = avatar
                ),
                onClick = { chooseAvatar() },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    vm.validateAndCreateProfile(addProfile)
                },
                enabled = vm.isValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.primaryContainer,
                    contentColor = colorScheme.onPrimaryContainer
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                    )

                    Spacer(
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    )

                    Text(
                        text = stringResource(R.string.create_profile),
                        style = typography.labelSmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreationScreenPreview() {
    MyMediaRyTheme(darkTheme = false) {
        CreationScreen(
            users = DataSource.users.map { it.name },
            avatar = R.drawable.add_circle,
            chooseAvatar = {},
            addProfile = {_, _ ->}
        )
    }
}