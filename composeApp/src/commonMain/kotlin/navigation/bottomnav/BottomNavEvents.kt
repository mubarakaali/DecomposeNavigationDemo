package navigation.bottomnav

sealed interface BottomNavEvents {
    data object NavAClick : BottomNavEvents
    data object NavBClick : BottomNavEvents
    data object NavCClick : BottomNavEvents
    data object NavDClick : BottomNavEvents
    data object NavEClick : BottomNavEvents
}