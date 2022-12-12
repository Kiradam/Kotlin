package hu.bme.aut.android.tetris_elao0e.models


class Jblock(x:Int, y:Int): Block(x,y){

    init {
        pB = Point(x - 1, y)
        pC = Point(x - 2, y)
        pD = Point(x, y - 1)
        rotate = 2
    }

    override fun moveDown() {
        pA.moveDown()
        pB.moveDown()
        pC.moveDown()
        pD.moveDown()
    }

    override fun moverotate() {
        if (state){
            var tempx = pB.x - pA.x
            var tempy = pB.y - pA.y

            pB.x = pA.x + tempy
            pB.y = pA.y - tempx
            pC.x = pA.x + 2*tempy
            pC.y = pA.y - 2*tempx
            tempx = pD.x - pA.x
            tempy = pD.y - pA.y
            pD.x = pA.x + tempy
            pD.y = pA.y - tempx
            state = false
        }else{
            var tempx = pB.x - pA.x
            var tempy = pB.y - pA.y
            pB.x = pA.x + tempy
            pB.y = pA.y - tempx
            pC.x = pA.x + 2*tempy
            pC.y = pA.y - 2*tempx
            tempx = pD.x - pA.x
            tempy = pD.y - pA.y
            pD.x = pA.x + tempy
            pD.y = pA.y - tempx
            state = true
        }
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
