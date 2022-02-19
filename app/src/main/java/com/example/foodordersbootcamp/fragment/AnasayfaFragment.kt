package com.example.foodordersbootcamp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.foodordersbootcamp.R
import com.example.foodordersbootcamp.adapter.YemeklerAdapter
import com.example.foodordersbootcamp.databinding.FragmentAnasayfaBinding
import com.example.foodordersbootcamp.viewmodel.AnasayfaFragmentViewModel

class AnasayfaFragment : Fragment() {
    private lateinit var tasarim: FragmentAnasayfaBinding
    private lateinit var adapter: YemeklerAdapter
    private lateinit var viewModel: AnasayfaFragmentViewModel

    //onCreateView de hangi layout ile çalışacağımızı belirtiyoruz.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false)

        viewModel.homePageRvAnim(tasarim)


        viewModel.yemeklerListesi.observe(viewLifecycleOwner, { yemeklerListesi ->
            adapter = YemeklerAdapter(requireContext(), yemeklerListesi)
            tasarim.yemeklerAdapter = adapter
        })

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnasayfaFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

}