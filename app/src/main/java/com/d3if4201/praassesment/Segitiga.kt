package com.d3if4201.praassesment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.d3if4201.praassesment.databinding.PersegiPanjangBinding
import com.d3if4201.praassesment.databinding.SegitigaBinding
import kotlinx.android.synthetic.main.persegi_panjang.*
import kotlinx.android.synthetic.main.segitiga.*
import kotlin.math.sqrt

class Segitiga : Fragment() {
    private lateinit var binding: SegitigaBinding
    private var alas: Double = 0.00
    private var tinggi: Double = 0.00
    private var sisiMiring: Double = 0.00
    private var luas: Double = 0.00
    private var keliling: Double = 0.00

    companion object{
        const val KEY_LUAS = "key_luas"
        const val KEY_KELILING = "key_keliling"
        const val KEY_SISIMIRING = "key_SISIMIRING"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.segitiga, container, false)

        if (savedInstanceState != null){
            sisiMiring = sqrt(alas*alas+tinggi*tinggi)
            luas = savedInstanceState.getDouble(KEY_LUAS)
            keliling = savedInstanceState.getDouble(KEY_KELILING)
        }
        binding.hitungSegitigaButton.setOnClickListener {
            if (savedInstanceState != null){
                Toast.makeText(this.activity, "Isi Panjang dan Lebar", Toast.LENGTH_SHORT)
            }else{
                alas = binding.alasText.text.toString().toDouble()
                tinggi = binding.tinggiText.text.toString().toDouble()
                luas = 0.5 * alas * tinggi
                keliling = alas + tinggi + sisiMiring
                binding.luasSegitiga.text = "Luas $luas /nKeliling = $keliling"
            }
        }
        binding.shareSegitigaButton.setOnClickListener {
            val luasSegitiga = luasSegitiga.text.toString()
            val share = Intent()
            share.action = Intent.ACTION_SEND
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_TEXT, luasSegitiga )
            share.putExtra(Intent.EXTRA_SUBJECT, "Hasil Hitung Luas dan keliling Segitiga" )
            startActivity(Intent.createChooser(share,"Share text via..."))

        }
        return binding.root
}
}