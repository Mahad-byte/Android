package com.example.bookingapp.mvvm.respoitory

import android.net.Uri
import android.util.Log
import com.example.bookingapp.mvvm.AppConstants
import com.example.bookingapp.mvvm.Model
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class HotelRepository {
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference
    private val auth = Firebase.auth
    //TODO: If user adds another hotel, it will override it
    private val db = FirebaseFirestore.getInstance()

    private lateinit var getImage: Uri

    //TODO: Rating not uploaded on firestore
    fun addHotelsFromFireStore(hotel: Model): Boolean{
        var result = false
        val data = Model(null, hotel.name, hotel.price, 4.6)
        db.collection(AppConstants.collectionName).document(auth.uid!!).set(data).addOnSuccessListener {
            result = true
        }.addOnFailureListener {
            result = false
        }
        return result
    }
    fun getHotelsFromFireStore(): Model{

        val temp = Model(null, null, null, null)
        db.collection(AppConstants.collectionName).document(auth.uid!!).get().addOnSuccessListener {
            if(it.exists()) {
                temp.name = it.data!!["name"].toString()
                temp.price = it.data!!["price"] as Double
                temp.rating = it.data!!["rating"] as? Double
                //Toast.makeText(context, "Gotten", Toast.LENGTH_SHORT).show()
                Log.i("Firebase Data", "$temp")
            }
        }.addOnFailureListener {

        }

        return temp
    }
    fun uploadImageToStorage(image: Uri): Boolean{
        var result = false

        //TODO: Image added with name images
        val Uimg = storageRef.child("${auth.uid}")
        val uploadTask = Uimg.putFile(image)
        uploadTask.addOnSuccessListener {
           result = true
        }.addOnFailureListener {
           result = false
        }
        return result
    }

    suspend fun getImageFromStorage(): Uri?{
        val pathReference = storageRef.child("images")

//        getImage = Uri.EMPTY
//
//
//        pathReference.downloadUrl.addOnCompleteListener {
//            if(it.isSuccessful) {
//                getImage = it.result
//            }else{
//                Toast.makeText(context, "Failed to get Image", Toast.LENGTH_SHORT).show()
//            }
//        }.addOnFailureListener {
//            Toast.makeText(context, "Failed to get Image", Toast.LENGTH_SHORT).show()
//        }
//        return  getImage
        return try {
            pathReference.downloadUrl.await()
        } catch (e: Exception) {
            null
        }

    }
}