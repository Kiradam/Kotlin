package hu.bme.aut.android.tetris_elao0e

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.bme.aut.android.tetris_elao0e.adapter.MenuPagerAdapter
import hu.bme.aut.android.tetris_elao0e.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vpMenuPanels.adapter = MenuPagerAdapter(supportFragmentManager)

    }
}