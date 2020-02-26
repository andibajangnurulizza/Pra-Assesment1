package com.d3if4201.praassesment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.d3if4201.praassesment.databinding.PersegiPanjangBinding
import kotlinx.android.synthetic.main.persegi_panjang.*

class PersegiPanjang : Fragment() {
    private lateinit var binding: PersegiPanjangBinding
    private var panjang: Double = 0.00
    private var lebar: Double = 0.00
    private var luas: Double = 0.00
    private var keliling: Double = 0.00

    companion object{
        const val KEY_LUAS = "key_luas"
        const val KEY_KELILING = "key_keliling"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.persegi_panjang, container, false)

        if (savedInstanceState != null){
            luas = savedInstanceState.getDouble(KEY_LUAS)
            keliling = savedInstanceState.getDouble(KEY_KELILING)
        }
        binding.hitungPersegiButton.setOnClickListener {
            if (savedInstanceState != null){
                Toast.makeText(this.activity, "Isi Panjang dan Lebar", Toast.LENGTH_SHORT)
            }else{
                panjang = binding.panjangText.text.toString().toDouble()
                lebar = binding.lebarText.text.toString().toDouble()
                luas = panjang * lebar
                keliling = 2*panjang + 2*lebar
                binding.luasPersegiText.text = "Luas $luas /nKeliling = $keliling"
            }
        }
        binding.sharePersegiButton.setOnClickListener {
            val luasPersegi = luasPersegiText.text.toString()
            val share = Intent()
            share.action = Intent.ACTION_SEND
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_TEXT, luasPersegi )
            share.putExtra(Intent.EXTRA_SUBJECT, "Hasil Hitung Luas dan keliling Persegi Panjang" )
            startActivity(Intent.createChooser(share,"Share text via..."))

        }


        return binding.root
    }

}