package hu.bme.aut.android.tetris_elao0e.models


class Oblock(x:Int, y:Int): Block(x,y){

    init {
        pB = Point(x + 1, y)
        pC = Point(x, y + 1)
        pD = Point(x + 1, y + 1)
    }

    override fun moveDown() {
        pA.moveDown()
        pB.moveDown()
        pC.moveDown()
        pD.moveDown()
    }

    override fun moverotate() {

    }

    override fun moveLeft() {
        pA.moveLeft()
        pB.moveLeft()
        pC.moveLeft()
        pD.moveLeft()
    }

    override fun moveRight() {
        pA.moveRight()
        pB.moveRight()
        pC.moveRight()
        pD.moveRight()
    }

}
