package navigation.bottomnav


data class NavItemModel(
    val title:String,
    val route:BottomNavEvents = BottomNavEvents.NavAClick
)

val navItems = listOf(
    NavItemModel(
        title = "Nav A",
        route = BottomNavEvents.NavAClick
    ),
    NavItemModel(
        title = "Nav B",
        route = BottomNavEvents.NavBClick
    ),
    NavItemModel(
        title = "Nav C",
        route = BottomNavEvents.NavCClick
    ),
    NavItemModel(
        title = "Nav D",
        route = BottomNavEvents.NavDClick
    ),
    NavItemModel(
        title = "Nav E",
        route = BottomNavEvents.NavEClick
    )
)