package hu.bme.aut.android.tetris_elao0e.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import hu.bme.aut.android.tetris_elao0e.ConfigActivity
import hu.bme.aut.android.tetris_elao0e.TableActivity
import hu.bme.aut.android.tetris_elao0e.databinding.FragmentMenuBinding

class MenuFragment: Fragment() {
    private lateinit var binding: FragmentMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        binding.btnstart.setOnClickListener{
            val i = Intent(context, TableActivity::class.java)
            startActivity(i)

        }
        binding.btnconfig.setOnClickListener{
            val i = Intent(context, ConfigActivity::class.java)
            startActivity(i)

        }
        binding.btnsearch.setOnClickListener{
            Toast.makeText(context, "Adam M. Balog ELAO0E", Toast.LENGTH_LONG).show()
        }
        //val animationdrawable: AnimationDrawable = binding.root.background as AnimationDrawable
        //animationdrawable.setEnterFadeDuration(2500)
        //animationdrawable.setExitFadeDuration(10000)
        //animationdrawable.start()

        return binding.root
    }
}
