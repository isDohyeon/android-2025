package hnu.multimedia.androiddh.week8.ui.util

class ColorUtil {

    companion object {
        const val RED = "#FF2F2F"
        const val BLUE = "#3F51B5"
        const val YELLOW = "#FFEB3B"
        const val PINK = "#FF80AB"

        fun getRandomColor(): String {
            val colors = listOf(RED, BLUE, YELLOW, PINK)
            return colors.random()
        }

        fun getColorName(color: String): String {
            return when (color) {
                RED -> "RED"
                PINK -> "PINK"
                YELLOW -> "YELLOW"
                BLUE -> "BLUE"
                else -> ""
            }
        }
    }
}