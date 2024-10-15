package com.brosedda.mymediary.ui

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.brosedda.mymediary.MainActivity
import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.model.User
import com.brosedda.mymediary.ui.components.appBar.UserAppBar
import com.brosedda.mymediary.ui.screens.users.AvatarSelectionScreen
import com.brosedda.mymediary.ui.screens.users.CreationScreen
import com.brosedda.mymediary.ui.screens.users.LoginScreen
import com.brosedda.mymediary.ui.screens.users.ProfilesScreen
import com.brosedda.mymediary.ui.state.UsersList
import com.brosedda.mymediary.ui.theme.MyMediaRyTheme
import com.brosedda.mymediary.ui.viewModel.users.UserViewModel

enum class UsersRoute(@StringRes val title:Int? = null) {
    Start,
    Login,
    Create(R.string.add_profile),
    Avatar(R.string.choose_avatar)
}

private fun goToMainApp(context: Context, navController: NavHostController, user: User) {
    val intent = Intent(context, MainActivity::class.java).apply {
        putExtra("NAME", user.name)
        putExtra("PASSWORD", user.password)
        putExtra("AVATAR", user.avatar)
    }

    navController.popBackStack(UsersRoute.Start.name, true)
    context.startActivity(intent)
}

@Composable
fun UserApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = UsersRoute.valueOf(
        backStackEntry?.destination?.route ?: UsersRoute.Start.name
    )
    val viewModel: UserViewModel = viewModel(factory = UserViewModel.Factory)

    Scaffold(
        topBar = {
            if (currentScreen != UsersRoute.Start) {
                UserAppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = {
                        navController.navigateUp()
                    }
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        Surface(
            color = colorScheme.primary,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = UsersRoute.Start.name,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))
            ) {
                composable(route = UsersRoute.Start.name) {
                    ProfilesScreen(
                        users = uiState.usersList,
                        navigateToLogin = {
                            viewModel.setCurrentUser(it)
                            navController.navigate(UsersRoute.Login.name)
                        },
                        navigateToCreation = {
                            viewModel.setCurrentUser(user = User(""))
                            navController.navigate(UsersRoute.Create.name)
                        }
                    )
                }

                composable(route = UsersRoute.Login.name) {
                    val context = LocalContext.current
                    val user = uiState.currentUser

                    if (user.password == null) goToMainApp(context, navController, user)

                    LoginScreen(
                        user = user,
                        connect = {
                            goToMainApp(context, navController, user)
                        }
                    )
                }

                composable(route = UsersRoute.Create.name) {
                    val users: List<User> = when (uiState.usersList) {
                        is UsersList.Success -> (uiState.usersList as UsersList.Success).users
                        else -> emptyList()
                    }

                    CreationScreen(
                       users = users.map { it.name },
                       avatar = uiState.currentUser.avatar,
                       chooseAvatar = { navController.navigate(UsersRoute.Avatar.name) },
                       addProfile = { name: String, password: String? ->
                           viewModel.addProfile(name, password)
                           navController.navigate(UsersRoute.Start.name)
                       }
                   )
                }

                composable(route = UsersRoute.Avatar.name) {
                    AvatarSelectionScreen (
                        chooseAvatar = { avatar ->
                            viewModel.setAvatar(avatar)
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun UserAppPreview() {
    MyMediaRyTheme(darkTheme = false) {
        UserApp()
    }
}
