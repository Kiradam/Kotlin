package hu.bme.aut.android.tetris_elao0e.models



abstract class Block(var x:Int, var y:Int){

    var pA = Point(x, y)
    lateinit var pB: Point
    lateinit var pC: Point
    lateinit var pD: Point

    var rotate = 0

    var state = true

    abstract fun moverotate()
    abstract fun moveDown()
    abstract fun moveLeft()
    abstract fun moveRight()

}