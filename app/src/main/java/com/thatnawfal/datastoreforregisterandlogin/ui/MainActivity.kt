package com.thatnawfal.datastoreforregisterandlogin.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Base64
import android.util.Base64.encodeToString
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.thatnawfal.datastoreforregisterandlogin.data.local.datastore.AccountDataStoreManager
import com.thatnawfal.datastoreforregisterandlogin.databinding.ActivityMainBinding
import com.thatnawfal.datastoreforregisterandlogin.ui.login.LoginActivity
import com.thatnawfal.datastoreforregisterandlogin.utils.viewModelFactory
import java.io.ByteArrayOutputStream
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var pref = AccountDataStoreManager(this)
    private val viewModel : MainViewModel by viewModelFactory {
        MainViewModel(pref)
    }

    private lateinit var uri: Uri
    private val cameraResult = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        result -> if (result) {
            saveToDataStore(uri)
        }
    }

    private val galleryResult = registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
        result?.let { saveToDataStore(it) }
    }

    private fun saveToDataStore(uriForBitmap: Uri) {
        val inputStream = contentResolver.openInputStream(uriForBitmap)
        val bitmapPhoto = BitmapFactory.decodeStream(inputStream)
        binding.image.setImageBitmap(bitmapPhoto)
        bitmapToString(bitmapPhoto)?.let { viewModel.setImage(it) }

    }

    companion object {
        const val REQUEST_CODE_PERMISSION = 100
    }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getUsername().observe(this@MainActivity){
            binding.tvHello.text = "Hello, $it"
        }

        viewModel.getImage().observe(this){
            binding.image.setImageBitmap(stringToBitmap(it))
        }

        binding.logout.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.changeImage.setOnClickListener {
            checkingPermission()
        }
    }

    private fun bitmapToString(bitmapPhoto: Bitmap?): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmapPhoto?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

        val byteArray = byteArrayOutputStream.toByteArray()

        return encodeToString(byteArray, Base64.DEFAULT)
    }

    private fun stringToBitmap(str: String): Bitmap? {
        return try {
            val encodeString = Base64.decode(str,Base64.DEFAULT)
            BitmapFactory.decodeByteArray(encodeString, 0 ,encodeString.size)
        } catch (e: Exception) {
            e.message
            null
        }
    }
    private fun checkingPermission(){
        if (isGranted(
                this, Manifest.permission.CAMERA,
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                REQUEST_CODE_PERMISSION,
            )
        ) {
          chooseImageDialog()
        }
    }

    private fun chooseImageDialog() {
        AlertDialog.Builder(this)
            .setMessage("Pilih Gambar")
            .setPositiveButton("Gallery") {_,_ -> openGallery()}
            .setNegativeButton("Camera") {_, _ -> openCamera()}
            .show()
    }

    private fun openCamera() {
        val photoFile = File.createTempFile(
            "IMG_",
            ".jpg",
            this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )

        uri = FileProvider.getUriForFile(
            this,
            "${this.packageName}.provider",
            photoFile
        )
        cameraResult.launch(uri)
    }

    private fun openGallery() {
        intent.type = "image/*"
        galleryResult.launch("image/*")
    }

    private fun isGranted(
        activity: MainActivity,
        permission: String,
        permissions: Array<String>,
        requestCodePermission: Int
    ): Boolean {
        val permissionCheck = ActivityCompat.checkSelfPermission(activity, permission)
        return if (permissionCheck != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showPermissionDeniedDialog()
            } else {
                ActivityCompat.requestPermissions(activity, permissions, requestCodePermission)
            }
            false
        } else {
            true
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission Denied")
            .setMessage("Permssion is denied, Lorem Ipsum .....")
            .setPositiveButton(
                "App Setting"
            ) { _, _ ->
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS

                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton("Cancel") {dialog, _ -> dialog.cancel()}
            .show()
    }
}