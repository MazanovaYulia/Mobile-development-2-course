package com.example.navapp.colorFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navapp.R
import com.example.navapp.databinding.FragmentBlueBinding


class BlueFragment : Fragment() {
    lateinit var binding: FragmentBlueBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlueBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentButton.setOnClickListener {
            MAIN.navController.navigate(R.id.action_blueFragment_to_redFragment)
        }
    }

}