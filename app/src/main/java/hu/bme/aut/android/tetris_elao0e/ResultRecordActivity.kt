package hu.bme.aut.android.tetris_elao0e


import android.content.Context
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.newJogoBt
import kotlinx.android.synthetic.main.activity_result.pontuacaoText
import kotlinx.android.synthetic.main.activity_result.sairBt
import kotlinx.android.synthetic.main.activity_result_record.*

class ResultRecordActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_record)

        val params = intent.extras
        val points = params?.getString("points")

        val settings = getSharedPreferences("records", Context.MODE_PRIVATE)
        val editor = settings.edit()

        pontuacaoText.text = points.toString()

        val record1 = settings.getInt("record1", 0)
        val record2 = settings.getInt("record2", 0)
        val record3 = settings.getInt("record3", 0)

        sairBt.setOnClickListener {
            if((points.toString().toInt()>=record3)&&(points.toString().toInt()<record2)){
                editor.putInt("record3", points.toString().toInt())
                editor.putString("name3", new_record_name.text.toString())
                editor.apply()
            }
            else if((points.toString().toInt()>=record2)&&(points.toString().toInt()<record1)){
                val tempname = settings.getString("name2", "")
                editor.putInt("record3", record2)
                editor.putString("name3", tempname)
                editor.putInt("record2", points.toString().toInt())
                editor.putString("name2", new_record_name.text.toString())
                editor.apply()
            }
            else if((points.toString().toInt()>=record1)){
                var tempname = settings.getString("name2", "")
                editor.putInt("record3", record2)
                editor.putString("name3", tempname)
                editor.putInt("record2", record1)
                tempname = settings.getString("name1", "")
                editor.putString("name2", tempname)
                editor.apply()
                editor.putInt("record1", points.toString().toInt())
                editor.putString("name1",new_record_name.text.toString())
                editor.apply()
            }

            finish()
        }

        newJogoBt.setOnClickListener {
            val i = Intent(this, TableActivity::class.java)
            startActivity(i)
        }

    }
}
