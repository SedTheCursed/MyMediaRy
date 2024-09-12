package com.brosedda.mymediary.ui.screens.users

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.DataSource
import com.brosedda.mymediary.data.model.User
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

@Composable
fun ProfileItem(
    user: User,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onClick() }
            .padding(bottom = dimensionResource(R.dimen.padding_medium))
    ) {
        Avatar(user.avatar)
        Text(
            text = user.name,
            style = typography.displayLarge
        )
    }

}

@Composable
fun Avatar(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(dimensionResource(R.dimen.profile_avatar_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(shapes.small)
    )
}

@Preview(
    showBackground = true
)
@Composable
fun ProfileScreenPreview() {
    MyMediaRyTheme(darkTheme = false) {
        ProfilesScreen(DataSource.users, { _ -> }, {})
    }
}