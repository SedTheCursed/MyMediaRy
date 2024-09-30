package com.brosedda.mymediary.ui.screens.users

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brosedda.mymediary.R
import com.brosedda.mymediary.ui.theme.MyMediaRyTheme
import com.brosedda.mymediary.ui.viewModel.users.AvatarViewModel

@Composable
fun AvatarSelectionScreen(
    chooseAvatar: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AvatarViewModel = viewModel()
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            items(viewModel.avatars) { avatar ->
                AvatarItem(
                    image = avatar,
                    select = { viewModel.selectAvatar(avatar) },
                    selected = viewModel.selected
                )
            }
        }

        Button(
            onClick = { chooseAvatar(viewModel.selected) },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.primaryContainer,
                contentColor = colorScheme.onPrimaryContainer
            ),
            modifier = Modifier.padding(
                top = dimensionResource(R.dimen.padding_medium)
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = null,
                )

                Spacer(
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )

                Text(
                    text = stringResource(R.string.choose_the_avatar),
                    style = typography.labelSmall
                )
            }
        }
    }
}

@Composable
fun AvatarItem(
    @DrawableRes image: Int,
    @DrawableRes selected: Int,
    select: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { select() },
        modifier = modifier
            .size(80.dp)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(shapes.small)
                .sectionBorder(image == selected)
        )
    }
}

@Composable
private fun Modifier.sectionBorder(condition: Boolean): Modifier {
    return when (condition) {
        false -> this
        true -> this.border(
            width =  7.dp,
            color = colorScheme.primaryContainer,
            shape = CircleShape
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AvatarSelectionScreenPreview() {
    MyMediaRyTheme(darkTheme = false) {
        AvatarSelectionScreen({_ -> })
    }
}