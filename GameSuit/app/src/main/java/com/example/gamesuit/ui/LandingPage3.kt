package com.example.gamesuit.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.gamesuit.databinding.FragmentLandingPage3Binding
import com.google.android.material.snackbar.Snackbar

class LandingPage3 : Fragment() {

    private lateinit var namaPlayer : String

    private lateinit var binding : FragmentLandingPage3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLandingPage3Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etNamaPlayer.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                binding.btnNext.visibility = View.VISIBLE
            } else {
                binding.btnNext.visibility = View.GONE
            }
            namaPlayer = binding.etNamaPlayer.text.toString()
        }
        binding.btnNext.setOnClickListener {
            Snackbar.make(it,"Selamat Datang "+namaPlayer, 2500).show()
            val intent = Intent (activity, HalamanMenuActivity::class.java)
            intent.putExtra("name", namaPlayer)
            activity?.finish()
            activity?.startActivity(intent)
        }
    }
}