package me.danbe.common.ui.navigation

sealed class Screen(val route:String){
    object MainScreen:Screen("main_screen")
}
