package com.brosedda.mymediary.ui.screens.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.DataSource
import com.brosedda.mymediary.data.model.User
import com.brosedda.mymediary.ui.components.userActivity.ProfileItem
import com.brosedda.mymediary.ui.theme.MyMediaRyTheme

@Composable
fun ProfilesScreen(
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

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MyMediaRyTheme(darkTheme = false) {
        ProfilesScreen(DataSource.users, { _ -> }, {})
    }
}