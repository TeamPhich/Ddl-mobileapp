package com.TeamPhich.deadline.ui.dashboard.details.change_info

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.TeamPhich.deadline.R
import com.TeamPhich.deadline.saveToken.SharedPreference
import com.TeamPhich.deadline.services.RetrofitClient
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_change_info.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import okhttp3.RequestBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import android.net.Uri




class changeInfo : AppCompatActivity() {
    var urlimage = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_info)
        val fullname = intent.getStringExtra("fullname")
        val email = intent.getStringExtra("email")
        val avatar = intent.getStringExtra("avatar")

        Glide
            .with(this)
            .load(avatar)
            .centerCrop()
            .placeholder(R.drawable.ic_insert_photo)
            .into(_new_info_avatar);
        _new_info_avatar
        _iNeweMail.setText(email)
        _iNewfullname.setText(fullname)
        urlimage = avatar


//        _exitchange.setOnClickListener {
//            finish()
//        }
//        _bchangepass.setOnClickListener {
//            val new = _iNeweMail.text.toString();
//            if (new.isEmpty()) {
//                _iNeweMail.error = "fullname required"
//                _iNeweMail.requestFocus()
//                return@setOnClickListener
//            }
//
//        }
        setavatar()
        _changeinfo.setOnClickListener {
            val newemail = _iNeweMail.text.toString();
            val newfullname = _iNewfullname.text.toString();
            if (newemail.isEmpty()) {
                _iNeweMail.error = "email required"
                _iNeweMail.requestFocus()
                return@setOnClickListener
            }

            if (newfullname.isEmpty()) {
                _iNewfullname.error = "full name  required"
                _iNewfullname.requestFocus()
                return@setOnClickListener
            }
            val sharedPreference: SharedPreference = SharedPreference(this)
            GlobalScope.launch(Dispatchers.Main) {

                try {
                    val response = RetrofitClient.instance.changeinfo(
                        sharedPreference.getTokenSpace().toString(),
                        newfullname,
                        newemail,
                        urlimage
                    ).await()
                    if (response.success == true) {
                        Toast.makeText(applicationContext, response.data.toString(), Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(applicationContext, response.reason, Toast.LENGTH_LONG)
                            .show()
                    }
                } catch (t: Throwable) {
                    Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                }


            }


        }


    }

    fun setavatar() {
        _new_info_avatar.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    // permission deny
                    val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
                } else {
                    // permission already granted
                    pickImageFromGalery()
                }
            } else {
                // system OS is < marshmallow
            }
        }
    }

    private fun pickImageFromGalery() {
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGalery()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getRealPathFromURI(contentUri: Uri): String {

        // can post image
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = managedQuery(
            contentUri,
            proj, // WHERE clause selection arguments (none)
            null, null, null
        )// Which columns to return
        // WHERE clause; which rows to return (all rows)
        // Order-by clause (ascending by name)
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()

        return cursor.getString(column_index)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val selectedImageURI = data?.data as Uri

            var filePath = getRealPathFromURI(selectedImageURI)
            val file = File(filePath)
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val requestFile =
                        RequestBody.create("application/octet-stream".toMediaTypeOrNull(), file)
                    val body = MultipartBody.Part.createFormData("image", file.name, requestFile)
                    val response = RetrofitClient.instance.postimage(body).await()
                    Glide
                        .with(this@changeInfo)
                        .load(response.data.url)
                        .centerCrop()
                        .placeholder(R.drawable.ic_insert_photo)
                        .into(_new_info_avatar)
                    runOnUiThread(
                        object : Runnable {
                            override fun run() {
                                urlimage=response.data.url
                            }
                        }
                    )
                } catch (t: Throwable) {
                    Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }


//    fun geturlimage(data: Intent?):String{
//
////        val file = FileUtils.getFile(this, fileUri)
////        val requestFile = RequestBody.create(
////            MediaType.parse(
////                contentResolver.getType(fileUri)),
////                file
////        )
////        GlobalScope.launch(Dispatchers.Main) {
////
////            try {
////                val response = RetrofitClient.instance.postimage(
////                    data
////                ).await()
////                if (response.success == true) {
////
////                } else {
////                    Toast.makeText(applicationContext, response.reason, Toast.LENGTH_LONG)
////                        .show()
////                }
////            } catch (t: Throwable) {
////                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
////            }
////
////
////        }
//    }
}