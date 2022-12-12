package hu.bme.aut.android.tetris_elao0e.models

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel(){

    private val lines = 22
    private val columns = 12

    var table = Array(lines) {
        Array(columns){0}
    }

}