@file:Suppress("DEPRECATION")

package hu.bme.aut.android.tetris_elao0e

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_table.*
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProviders
import hu.bme.aut.android.tetris_elao0e.models.*
import kotlin.random.Random

class TableActivity : AppCompatActivity() {

    private val line = 22
    private val column = 12

    private var running = true

    private var speed = longArrayOf(250,500,100)

    private var p = generatePiece(1)

    var points = 0
    private var chosen = 0
    private var current = 2

    private val game: GameViewModel by lazy {
        ViewModelProviders.of(this)[GameViewModel::class.java]
    }

    private var boardView = Array(line){
        arrayOfNulls<ImageView>(column)
    }

    private var nextpiece = 1
    private var random = Random

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_table)

        //get prefs
        val settings = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        chosen = settings.getInt("difficulty", 0)


        //gridboard size
        gridboard.rowCount = line
        gridboard.columnCount = column

        val inflater = LayoutInflater.from(this)

        for (i in 0 until line) {
            for (j in 0 until column) {
                R.layout.inflate_image_view
                boardView[i][j] = inflater.inflate(R.layout.inflate_image_view, gridboard, false) as ImageView
                gridboard.addView( boardView[i][j])
            }
        }

        gameRun()

        leftBt.setOnClickListener {
            if(!checkleft())
                p.moveLeft()

        }

        rightBt.setOnClickListener {
            if(!checkright())
                p.moveRight()
        }

        downBt.setOnClickListener {
            if(possibledown())
                p.moveDown()

        }

        rotateBt.setOnClickListener {
            rotate()
        }

    }

    private fun gameRun(){
        Thread{
            while(running){
                Thread.sleep(speed[chosen])
                runOnUiThread{
                    //clean screen
                    for (i in 0 until line) {
                        for (j in 0 until column) {
                            if (game.table[i][j] == 0){
                                boardView[i][j]!!.setImageResource(R.drawable.branco)
                            }
                        }
                    }

                    //check if lost
                    lostgame()

                    //show points
                    currentpointText.text = points.toString()

                    if(hitpiece() || hitfinal()){
                        update_table()
                        verifypoints()
                        fazerBlock()
                        newBlock()
                    }else{
                        p.moveDown()
                    }

                    try {
                        fazerBlock()
                    }catch (e:ArrayIndexOutOfBoundsException) {
                        e.printStackTrace()
                    }

                }
            }
        }.start()
    }

    private fun update_table(){
        game.table[p.pA.x][p.pA.y] =1
        game.table[p.pB.x][p.pB.y] =1
        game.table[p.pC.x][p.pC.y] =1
        game.table[p.pD.x][p.pD.y] =1
    }

    private fun fazerBlock(){
        when (current) {
            0 -> {
                boardView[p.pA.x][p.pA.y]!!.setImageResource(R.drawable.turkiz)
                boardView[p.pB.x][p.pB.y]!!.setImageResource(R.drawable.turkiz)
                boardView[p.pC.x][p.pC.y]!!.setImageResource(R.drawable.turkiz)
                boardView[p.pD.x][p.pD.y]!!.setImageResource(R.drawable.turkiz)
            }
            1 -> {
                boardView[p.pA.x][p.pA.y]!!.setImageResource(R.drawable.orange)
                boardView[p.pB.x][p.pB.y]!!.setImageResource(R.drawable.orange)
                boardView[p.pC.x][p.pC.y]!!.setImageResource(R.drawable.orange)
                boardView[p.pD.x][p.pD.y]!!.setImageResource(R.drawable.orange)
            }
            2 -> {
                boardView[p.pA.x][p.pA.y]!!.setImageResource(R.drawable.darkblue)
                boardView[p.pB.x][p.pB.y]!!.setImageResource(R.drawable.darkblue)
                boardView[p.pC.x][p.pC.y]!!.setImageResource(R.drawable.darkblue)
                boardView[p.pD.x][p.pD.y]!!.setImageResource(R.drawable.darkblue)
            }
            3 -> {
                boardView[p.pA.x][p.pA.y]!!.setImageResource(R.drawable.yellow)
                boardView[p.pB.x][p.pB.y]!!.setImageResource(R.drawable.yellow)
                boardView[p.pC.x][p.pC.y]!!.setImageResource(R.drawable.yellow)
                boardView[p.pD.x][p.pD.y]!!.setImageResource(R.drawable.yellow)
            }
            4 -> {
                boardView[p.pA.x][p.pA.y]!!.setImageResource(R.drawable.neongreen)
                boardView[p.pB.x][p.pB.y]!!.setImageResource(R.drawable.neongreen)
                boardView[p.pC.x][p.pC.y]!!.setImageResource(R.drawable.neongreen)
                boardView[p.pD.x][p.pD.y]!!.setImageResource(R.drawable.neongreen)
            }
            5 -> {
                boardView[p.pA.x][p.pA.y]!!.setImageResource(R.drawable.pink)
                boardView[p.pB.x][p.pB.y]!!.setImageResource(R.drawable.pink)
                boardView[p.pC.x][p.pC.y]!!.setImageResource(R.drawable.pink)
                boardView[p.pD.x][p.pD.y]!!.setImageResource(R.drawable.pink)
            }
            6 -> {
                boardView[p.pA.x][p.pA.y]!!.setImageResource(R.drawable.red)
                boardView[p.pB.x][p.pB.y]!!.setImageResource(R.drawable.red)
                boardView[p.pC.x][p.pC.y]!!.setImageResource(R.drawable.red)
                boardView[p.pD.x][p.pD.y]!!.setImageResource(R.drawable.red)
            }
        }

    }

    private fun newBlock(){

        val newP = nextpiece

        Log.i("test","$newP")

        nextpiece = random.nextInt(7)


        when (nextpiece) {
            0 -> imageProxima.setImageResource(R.drawable.i)
            2 -> imageProxima.setImageResource(R.drawable.jj)
            1 -> imageProxima.setImageResource(R.drawable.ll)
            3 -> imageProxima.setImageResource(R.drawable.o)
            4 -> imageProxima.setImageResource(R.drawable.s)
            5 -> imageProxima.setImageResource(R.drawable.t)
            else -> imageProxima.setImageResource(R.drawable.z)
        }

        p = generatePiece(newP)
    }

    private fun generatePiece(newpiece:Int): Block {

        when (newpiece) {
            0 -> {
                current = 0
                return Iblock(1, 6)}
            1 -> {
                current = 1
                return Lblock(2, 6)}
            2 -> {
                current = 2
                return Jblock(2, 6)}
            3 -> {
                current = 3
                return Oblock(0, 6)}
            4 -> {
                current = 4
                return Sblock(1, 6)}
            5 -> {
                current = 5
                return Tblock(0, 6)}
            else -> {
                current = 6
                return Zblock(0, 6)}
        }

    }

    private fun hitpiece():Boolean{
        try {
            if((game.table[p.pA.x+1][p.pA.y] == 1) ||
                (game.table[p.pB.x+1][p.pB.y] == 1) ||
                (game.table[p.pC.x+1][p.pC.y] == 1) ||
                (game.table[p.pD.x+1][p.pD.y] == 1)){
                return true
            }
        }catch (e:ArrayIndexOutOfBoundsException){
            e.printStackTrace()
        }
        return false
    }

    private fun hitfinal():Boolean {
        if (p.pA.x+1 >= line ||
            p.pB.x+1 >= line ||
            p.pC.x+1 >= line ||
            p.pD.x+1 >= line){
            return true
        }
        return false
    }

    private fun checkright():Boolean{
        try {
            if(((game.table[p.pA.x][p.pA.y+1] == 0) &&
                        (game.table[p.pB.x][p.pB.y+1] == 0) &&
                        (game.table[p.pC.x][p.pC.y+1] == 0) &&
                        (game.table[p.pD.x][p.pD.y+1] == 0)) ||
                (p.pA.y+1 == column-1 ||
                        p.pB.y+1 == column-1 ||
                        p.pC.y+1 == column-1 ||
                        p.pD.y+1 == column-1) ){
                return false
            }
        }catch (e:ArrayIndexOutOfBoundsException){
            Log.i("MISTAKE","Right Error")
        }
        return true
    }

    private fun checkleft():Boolean{
        try {
            if(((game.table[p.pA.x][p.pA.y-1] == 0) &&
                        (game.table[p.pB.x][p.pB.y-1] == 0) &&
                        (game.table[p.pC.x][p.pC.y-1] == 0) &&
                        (game.table[p.pD.x][p.pD.y-1] == 0)) ||
                (p.pA.y-1 == 0 ||
                        p.pB.y-1 == 0 ||
                        p.pC.y-1 == 0 ||
                        p.pD.y-1 == 0)){
                return false
            }
        }catch (e:ArrayIndexOutOfBoundsException){
            Log.i("ERROR","Left Error")
        }
        return true
    }

    private fun possibledown():Boolean{
        if (p.pA.x+1 >= line ||
            p.pB.x+1 >= line ||
            p.pC.x+1 >= line ||
            p.pD.x+1 >= line) {
            return false
        }else if((game.table[p.pA.x+1][p.pA.y] == 1) ||
            (game.table[p.pB.x+1][p.pB.y] == 1) ||
            (game.table[p.pC.x+1][p.pC.y] == 1) ||
            (game.table[p.pD.x+1][p.pD.y] == 1)){
            return false
        }
        return true
    }

    fun verifypoints(){
        for (i in 0 until line) {
            if (game.table[i].sum() == 12){
                deleterow(i)
                points +=(1 * 250 * 100/speed[chosen]).toInt()
            }
        }
    }

    fun deleterow(line:Int){
        for (i in line downTo  1) {
            game.table[i] = game.table[i-1]
        }

    }

    private fun rotate(){

        //save position
        val PointA = Point(p.pA.x, p.pA.y)
        val PointB = Point(p.pB.x, p.pB.y)
        val PointC = Point(p.pC.x, p.pC.y)
        val PointD = Point(p.pD.x, p.pD.y)

        //find place to turn
        while (p.pA.y < p.rotate || p.pA.y > (column-1) - p.rotate){
            if (p.pA.y<p.rotate){
                p.moveRight()
            }else{
                p.moveLeft()
            }
        }

        //check space to rotate (bottom)
        if (p.pA.x < line - p.rotate) {
            p.moverotate()
        }

        //check if it hits any part and if it hits it goes back to what it was before
        if ((game.table[p.pA.x][p.pA.y] == 1) ||
            (game.table[p.pB.x][p.pB.y] == 1) ||
            (game.table[p.pC.x][p.pC.y] == 1) ||
            (game.table[p.pD.x][p.pD.y] == 1)){

            p.pA = Point(PointA.x, PointA.y)
            p.pB = Point(PointB.x, PointB.y)
            p.pC = Point(PointC.x, PointC.y)
            p.pD = Point(PointD.x, PointD.y)

        }

    }

    private fun hitUp():Boolean{
        if (p.pA.x == 0 ||
            p.pB.x == 0 ||
            p.pC.x == 0 ||
            p.pD.x == 0) {
            return true
        }
        return false
    }

    fun lostgame(){

        if(hitpiece() && hitUp()){
            super.onPause()
            running = false

            val settings = getSharedPreferences("records", Context.MODE_PRIVATE)
            //var editor = settings.edit()
            val record1 = settings.getInt("record1", 0)
            val record2 = settings.getInt("record2", 0)
            val record3 = settings.getInt("record3", 0)
            var i = Intent(this, ResultNorecordActivity::class.java)
            if((points.toString().toInt()>=record3)&&(points.toString().toInt()<record2)){
                i = Intent(this, ResultRecordActivity::class.java)
            }
            else if((points.toString().toInt()>=record2)&&(points.toString().toInt()<record1)){
                i = Intent(this, ResultRecordActivity::class.java)
            }
            else if((points.toString().toInt()>=record1)){
                i = Intent(this, ResultRecordActivity::class.java)
            }

            val pointing = currentpointText.text.toString()
            val b = Bundle()
            b.putString("points", pointing)
            i.putExtras(b)
            Log.i("ERROR", "Entered lost state")
            startActivity(i)

            finish()
        }

    }

    override fun onPause() {
        super.onPause()
        running = false
    }

    override fun onRestart() {
        super.onRestart()
        running=true
        gameRun()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            99 -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val i = Intent(this, TableActivity::class.java)
                        startActivity(i)
                    }
                }
            }
        }

    }

}