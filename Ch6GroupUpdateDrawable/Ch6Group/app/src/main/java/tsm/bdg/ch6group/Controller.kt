package  tsm.bdg.ch6group

class Controller(private val callback: CallBack){
    fun banding(one: String, two: String) = when (one + two) {
        "gunting" + "gunting", "batu" + "batu", "kertas" + "kertas" -> {
            callback.showResult("SERI")
        }

        "gunting" + "kertas", "kertas" + "batu", "batu" + "gunting" -> {
            callback.showResult("MENANG")
        }

        "kertas" + "gunting", "batu" + "kertas", "gunting" + "batu" -> {
            callback.showResult("KALAH")
        }
        else -> fun (){}
    }
}
