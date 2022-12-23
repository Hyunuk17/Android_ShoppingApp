package com.example.shoppingapp.ui.login

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppingapp.DBHelper
import com.example.shoppingapp.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        // 가입 버튼 누르면
        binding.signupConfirm.setOnClickListener {
            val id = binding.signupId.text.toString()
            val pw = binding.signupPw.text.toString()
            val phone = binding.signupPhone.text.toString()
            val email = binding.signupEmail.text.toString()

            if(id == "") {
                Toast.makeText(this@SignUpActivity, "Please enter your Id", Toast.LENGTH_LONG).show()
            }
            else {
                if(pw == "") {
                    Toast.makeText(this@SignUpActivity, "Please enter your Password", Toast.LENGTH_LONG).show()
                }
                else {
                    if(phone == "") {
                        Toast.makeText(this@SignUpActivity, "Please enter your phone number", Toast.LENGTH_LONG).show()
                    }
                    else {
                        if(email == "") {
                            Toast.makeText(this@SignUpActivity, "Please enter your E-mail address", Toast.LENGTH_LONG).show()
                        }
                        else { // null check 완료ㄹㅇ
//                            val user = User(id, pw, phone, email) // User 객체 만듬
//                            UserList.userList.add(user) // UserList에 등록 : 대신 DB에 등록 -> DB에서 리스트 끌어쓰기

                            // DB에 등록
                            var dbHelper = DBHelper(this, "mydb.db", null, 1)
                            var database: SQLiteDatabase = dbHelper.writableDatabase
                            var query = ("INSERT INTO user(id, pw, phone, email) VALUES ('${id}', '${pw}', '${phone}', '${email}');")
                            database.execSQL(query)
                            // DB에서 UserList에 등록
                            query = "SELECT * FROM user WHERE id = '${id}';"
                            var cursor = database.rawQuery(query, null)
                            cursor.moveToNext()

                            var _id = cursor.getString(cursor.getColumnIndex("id"))
                            var _pw = cursor.getString(cursor.getColumnIndex("pw"))
                            var _phone = cursor.getString(cursor.getColumnIndex("phone"))
                            var _email = cursor.getString(cursor.getColumnIndex("email"))
                            var user = User(_id, _pw, _phone, _email)

                            UserList.userList.add(user)

                            Toast.makeText(this@SignUpActivity, "Sign up complete", Toast.LENGTH_LONG).show()
                            finish() // LoginActivity로 복귀
                        }
                    }
                }
            }
        }

        binding.signupCancel.setOnClickListener {
            finish()
        }

        binding.close2.setOnClickListener {
            finish()
        }
    }
}