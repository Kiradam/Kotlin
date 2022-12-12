package hu.bme.aut.android.tetris_elao0e.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import hu.bme.aut.android.tetris_elao0e.databinding.FragmentRecordBinding


class RecordFragment: Fragment() {
    private lateinit var binding: FragmentRecordBinding;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRecordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    @SuppressLint("SetTextI18n")
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            requireFragmentManager().beginTransaction().detach(this).attach(this).commit()
            Log.i("IsRefresh", "Yes")
            val settings = activity?.getSharedPreferences("records", Context.MODE_PRIVATE)
            var a = settings?.getString("name1", "")
            if (a != null) {
                Log.i("IsRefresh", a)
            }
            binding.recordname1.text = settings?.getString("name1", "")
            binding.recordname2.text = settings?.getString("name2", "")
            binding.recordname3.text = settings?.getString("name3", "")
            binding.recordname12.text = settings?.getInt("record1", 0).toString()
            binding.recordname13.text = settings?.getInt("record2", 0).toString()
            binding.recordname15.text = settings?.getInt("record3", 0).toString()
            //val animationdrawable: AnimationDrawable = binding.root.background as AnimationDrawable
            //animationdrawable.setEnterFadeDuration(0)
            //animationdrawable.setExitFadeDuration(10000)
            //animationdrawable.start()
        }
    }
}