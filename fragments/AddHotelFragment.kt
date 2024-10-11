package com.example.bookingapp.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bookingapp.databinding.FragmentAddHotelBinding
import com.example.bookingapp.mvvm.Model
import com.example.bookingapp.mvvm.respoitory.HotelRepository
import com.example.bookingapp.mvvm.viewmodel.MyViewModel
import com.example.bookingapp.mvvm.viewmodel.MyViewModelFactory

class AddHotelFragment : Fragment() {

    private lateinit var viewModel: MyViewModel
    private lateinit var pickImage: ActivityResultLauncher<String>
    private lateinit var addHotelBinding: FragmentAddHotelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){
            if(it != null){
                uploadHotelToFirebase(it)
            }
        }
        val repository = HotelRepository()
        val factory = MyViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addHotelBinding = FragmentAddHotelBinding.inflate(inflater, container, false)
        return addHotelBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addHotelBinding.getImg.setOnClickListener {
            openGallery()
        }
        addHotelBinding.getRecord.setOnClickListener {
//            val hotel = HotelRepository()
//            hotel.getHotelsFromFireStore(requireContext())
        }
    }

    private fun uploadHotelToFirebase(imageUri: Uri) {
        val hotelName = addHotelBinding.getName.text.toString()
        val hotelPrice = addHotelBinding.getPrice.text.toString().toDouble()

        //TODO: Image size causes runtime exception
        val hotelImage = addHotelBinding.hotelImage
        hotelImage.setImageURI(imageUri)

        //TODO: Rating is set by default
        val model = Model(imageUri ,hotelName, hotelPrice, 4.6)
        val hotel = HotelRepository()
        addHotelBinding.saveRecord.setOnClickListener {

            viewModel.addHotels(model)

           //delay(1000)
            addHotelBinding.hotelImage.setImageURI(null)
            addHotelBinding.getName.text?.clear()
            addHotelBinding.getPrice.text?.clear()
        }
    }

    private fun openGallery() {
        pickImage.launch("image/*")
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddHotelFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}