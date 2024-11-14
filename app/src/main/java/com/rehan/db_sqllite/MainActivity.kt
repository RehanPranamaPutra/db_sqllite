package com.rehan.db_sqllite


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rehan.db_sqllite.helper.DbHelper
import java.util.jar.Attributes.Name

class MainActivity : AppCompatActivity() {

    private lateinit var etName : EditText
    private lateinit var etKampus : EditText
    private lateinit var etEmail : EditText
    private lateinit var etPhone : EditText
    private lateinit var btnSubmit : Button
    private lateinit var btnShowData : Button
    private lateinit var txtNama : TextView
    private lateinit var txtKampus : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etNamaLengkap)
        etKampus = findViewById(R.id.etNamaKampus)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnShowData = findViewById(R.id.btnShowData)
        txtNama = findViewById(R.id.txtNama)
        txtKampus = findViewById(R.id.txtKampus)

        //add data

       btnSubmit.setOnClickListener {
           val dbHelper = DbHelper(this,null)

           //get dat dari widget edit text

           val name = etName.text.toString()
           val kampus = etKampus.text.toString()
           val email = etEmail.text.toString()
           val phone = etPhone.text.toString()


           dbHelper.addName(
               name,
               kampus,
               email,
               phone
           )

           // kota tambahkan notif
           Toast.makeText(this,name + "Data berhasil di input ke db",
               Toast.LENGTH_LONG)

           //ketika berhasil input, kita clear semua isi di widget

           etName.text.clear()
           etKampus.text.clear()
           etEmail.text.clear()
           etPhone.text.clear()
       }

        btnShowData.setOnClickListener(){
            val db = DbHelper(this,null)

            val cursor = db.getName()
            cursor!!.moveToFirst() // ngambil data pertama atau yang terbaru

            txtNama.append(cursor.getString(cursor.getColumnIndex(DbHelper.NAMA_LENGKAP)) + "\n")
            txtKampus.append(cursor.getString(cursor.getColumnIndex(DbHelper.NAMA_KAMPUS)) + "\n")

            while (cursor.moveToNext()){
                txtNama.append(cursor.getString(cursor.getColumnIndex(DbHelper.NAMA_LENGKAP)) + "\n")
                txtKampus.append(cursor.getString(cursor.getColumnIndex(DbHelper.NAMA_KAMPUS)) + "\n")
            }
            cursor.close()


        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}