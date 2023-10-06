package com.example.challenge2_binar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2_binar.R
import com.example.challenge2_binar.adapter.KeranjangAdapter
import com.example.challenge2_binar.databinding.FragmentKeranjangBinding
import com.example.challenge2_binar.viewModel.KeranjangViewModel
import com.example.challenge2_binar.viewModel.ViewModelFactory


class KeranjangFragment : Fragment() {
    private lateinit var binding: FragmentKeranjangBinding
    private lateinit var keranjangViewModel: KeranjangViewModel
    private lateinit var keranjangAdapter: KeranjangAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKeranjangBinding.inflate(inflater, container, false)
        val viewModelFactory = ViewModelFactory(requireActivity().application)
        keranjangViewModel = ViewModelProvider(this, viewModelFactory)[KeranjangViewModel::class.java]

        keranjangAdapter = KeranjangAdapter(keranjangViewModel)
        binding.rvKeranjang.setHasFixedSize(true)
        binding.rvKeranjang.layoutManager = LinearLayoutManager(requireContext())
        binding.rvKeranjang.adapter = keranjangAdapter

        keranjangViewModel.allCartItems.observe(viewLifecycleOwner) {
            keranjangAdapter.setData(it)
        }
        keranjangViewModel.cartItemLiveData.observe(viewLifecycleOwner) {}
        keranjangViewModel.totalPrice.observe(viewLifecycleOwner) {
            binding.tvTotalHargaPesanan.text = it.toString()
        }


        return binding.root
    }
}