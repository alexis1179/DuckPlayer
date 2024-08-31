package com.alexis1179.duckplayer.utils
enum class Routes{
    HOME
}

sealed class NavigationItem(val route: String){
    object Home: NavigationItem(Routes.HOME.name)
}