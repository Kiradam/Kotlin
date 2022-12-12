package hu.bme.aut.android.tetris_elao0e


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.newJogoBt
import kotlinx.android.synthetic.main.activity_result.pontuacaoText
import kotlinx.android.synthetic.main.activity_result.sairBt
import kotlinx.android.synthetic.main.activity_result_record.*

class ResultNorecordActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val params = intent.extras
        val points = params?.getString("points")

        val settings = getSharedPreferences("records", Context.MODE_PRIVATE)
        val editor = settings.edit()

        pontuacaoText.text = points.toString()

        val record = settings.getInt("record", 0)

        if(points.toString().toInt()>record){
            newRecordText.visibility = View.VISIBLE
            editor.putInt("record", points.toString().toInt())
            editor.apply()
        }

        sairBt.setOnClickListener {
            finish()
        }

        newJogoBt.setOnClickListener {
            val i = Intent(this, TableActivity::class.java)
            startActivity(i)
        }

    }
}
