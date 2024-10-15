package com.brosedda.mymediary.ui.screens.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.DataSource
import com.brosedda.mymediary.data.model.User
import com.brosedda.mymediary.ui.components.LoadingScreen
import com.brosedda.mymediary.ui.components.userActivity.ProfileItem
import com.brosedda.mymediary.ui.state.UsersList
import com.brosedda.mymediary.ui.theme.MyMediaRyTheme

@Composable
fun ProfilesScreen(
    users: UsersList,
    navigateToLogin: (User) -> Unit,
    navigateToCreation: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (users) {
        is UsersList.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is UsersList.Error -> ErrorList(modifier = modifier.fillMaxSize())
        is UsersList.Success -> UserSelector(
            users = users.users,
            navigateToLogin = navigateToLogin,
            navigateToCreation = navigateToCreation,
            modifier = modifier
        )
    }
}

@Composable
fun UserSelector(
    users: List<User>,
    navigateToLogin: (User) -> Unit,
    navigateToCreation: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ){
        LazyColumn {
            items(users) {
                ProfileItem(
                    user = it,
                    onClick = { navigateToLogin(it) }
                )
            }
        }

        ProfileItem(
            user = User(
                name = stringResource(R.string.add_profile),
                avatar = R.drawable.add_circle
            ),
            onClick = { navigateToCreation() }
        )
    }
}

@Composable
fun ErrorList(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.users_not_found),
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MyMediaRyTheme(darkTheme = false) {
        ProfilesScreen(UsersList.Success(DataSource.users), { _ -> }, {})
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorProfileScreenPreview() {
    MyMediaRyTheme(darkTheme = false) {
        ProfilesScreen(UsersList.Error, { _ -> }, {})
    }
}