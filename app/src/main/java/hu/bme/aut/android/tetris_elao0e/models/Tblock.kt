package hu.bme.aut.android.tetris_elao0e.models


class Tblock(x:Int, y:Int): Block(x,y){

    init {
        pB = Point(x, y - 1)
        pC = Point(x, y + 1)
        pD = Point(x + 1, y)
        rotate = 1
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

            tempx = pC.x - pA.x
            tempy = pC.y - pA.y
            pC.x = pA.x + tempy
            pC.y = pA.y - tempx
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
            tempx = pC.x - pA.x
            tempy = pC.y - pA.y
            pC.x = pA.x + tempy
            pC.y = pA.y - tempx
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
