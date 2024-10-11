package com.example.bookingapp.mvvm.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookingapp.mvvm.Model
import com.example.bookingapp.mvvm.respoitory.HotelRepository
import kotlinx.coroutines.launch

class MyViewModel(private val repository: HotelRepository): ViewModel() {
    private val _hotels = MutableLiveData<Model>()
    val hotels: LiveData<Model> get() = _hotels

    private val _addhotels = MutableLiveData<Boolean>()
    val addhotels: LiveData<Boolean> get() = _addhotels

    private val _addhotelPic = MutableLiveData<Boolean>()
    val addhotelPic: LiveData<Boolean> get() = _addhotelPic

    private val _gethotelPic = MutableLiveData<Uri>()
    val gethotelPic: LiveData<Uri> get() = _gethotelPic

    fun fetchHotels(){
        viewModelScope.launch {
            val data = repository.getHotelsFromFireStore()
            _hotels.postValue(data)
        }
    }
    fun addHotels(hotel: Model){
        viewModelScope.launch {
            val data = repository.addHotelsFromFireStore(hotel)
            _addhotels.postValue(data)
        }
    }
    fun fetchHotelPicture(){
        viewModelScope.launch {
            val data = repository.getImageFromStorage()
            _gethotelPic.postValue(data)
        }
    }
    fun addHotelPicture(image: Uri){
        viewModelScope.launch {
            val data = repository.uploadImageToStorage(image)
            _addhotelPic.postValue(data)
        }
    }
}