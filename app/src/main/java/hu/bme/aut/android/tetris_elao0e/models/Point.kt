package hu.bme.aut.android.tetris_elao0e.models

class Point (var x:Int, var y:Int){

    fun moveDown() {
       x++
    }

    fun moveLeft(){
        y--
    }

    fun moveRight(){
        y++
    }

}