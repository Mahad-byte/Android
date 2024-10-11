package com.example.bookingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookingapp.databinding.FragmentHomeBinding
import com.example.bookingapp.mvvm.respoitory.HotelRepository
import com.example.bookingapp.mvvm.viewmodel.MyViewModel
import com.example.bookingapp.mvvm.viewmodel.MyViewModelFactory
import com.example.bookingapp.recyclerView.Adapter

class HomeFragment : Fragment() {
    private lateinit var rvBinding: FragmentHomeBinding
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvBinding = FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rvBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return rvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvBinding.hotelList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false )


        val repository = HotelRepository()
        val factory = MyViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)
        viewModel.fetchHotelPicture()
        val hotel = HotelRepository()

        val model = hotel.getHotelsFromFireStore()

        //TODO: See this concept
        viewModel.gethotelPic.observe(viewLifecycleOwner) { imgUri ->
            model.image = imgUri
        }

        //Only updates on realtime values :/
        val list = mutableListOf(model)
        list.add(model)
        list.add(model)
        rvBinding.hotelList.adapter = Adapter(requireContext(),list)


    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}