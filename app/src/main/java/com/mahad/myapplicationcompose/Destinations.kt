package com.mahad.myapplicationcompose

interface Destinations {
    val route: String
}

object offers: Destinations{
    override val route = "Home"
}

object title: Destinations{
    override val route = "title"
}