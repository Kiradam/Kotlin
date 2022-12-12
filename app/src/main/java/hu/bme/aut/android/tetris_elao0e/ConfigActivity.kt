package hu.bme.aut.android.tetris_elao0e

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_config.*

class ConfigActivity : AppCompatActivity() {

    var base_diff=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        var b = Bundle()

        val settings = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        base_diff = settings.getInt("difficulty", 0)
        var editor = settings.edit()
        if(base_diff==0)
            normalRB.isChecked=true
        else  if(base_diff==1)
            facilRB.isChecked=true
        else
            dificilRB.isChecked=true

        saveBt.setOnClickListener {
            if (normalRB.isChecked)
                editor.putInt("difficulty", 0)
                editor.apply()
            if(facilRB.isChecked)
                editor.putInt("difficulty", 1)
                editor.apply()
            if (dificilRB.isChecked)
                editor.putInt("difficulty", 2)
                editor.apply()

            var i = Intent(this, MenuActivity::class.java)
            setResult(Activity.RESULT_OK, i)
            finish()
        }

    }
}