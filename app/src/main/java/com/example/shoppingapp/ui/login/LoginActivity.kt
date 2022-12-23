package com.example.shoppingapp.ui.login

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppingapp.DBHelper
import com.example.shoppingapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar:ActionBar? = supportActionBar
        actionBar?.hide()

        var dbHelper = DBHelper(this, "mydb.db", null, 1)
        var database: SQLiteDatabase = dbHelper.writableDatabase
        UserList.userList = mutableListOf()
        // DB에 있는값 UserList로 만들기
        var query = "SELECT * FROM user;"
        var cursor = database.rawQuery(query, null)
        while(cursor.moveToNext()) {
            var _id = cursor.getString(cursor.getColumnIndex("id"))
            var _pw = cursor.getString(cursor.getColumnIndex("pw"))
            var _phone = cursor.getString(cursor.getColumnIndex("phone"))
            var _email = cursor.getString(cursor.getColumnIndex("email"))
            var user = User(_id, _pw, _phone, _email)

            UserList.userList.add(user)
        }

        // edit text 사용법?
        // 로그인 버튼 눌렀을 때 editText 값 가져오기
        binding.loginLogin.setOnClickListener {
            val id = binding.loginId.text.toString()
            val pw = binding.loginPw.text.toString()

            var check = 0
            // 입력한 Id와 PW로 UserList 검색 for 돌면서 일치하는지 확인
            for(i in 0 until UserList.userList.size) {
                if(id == UserList.userList[i].id) {
                    check = 1
                    if(pw == UserList.userList[i].pw) { // 로그인 성공 : Login 객체 생성
                        Login.login = User(UserList.userList[i].id, UserList.userList[i].pw, UserList.userList[i].phone, UserList.userList[i].email)
                        Toast.makeText(this@LoginActivity, "Welcome ${Login.login!!.id}", Toast.LENGTH_LONG).show()
                        finish()
                    }
                    else{// 비밀번호가 틀림
                        Toast.makeText(this@LoginActivity, "Incorrect password", Toast.LENGTH_LONG).show()
                        break
                    }
                }
                else {
                    check = 0
                }
            }
            // 등록된 ID가 없음
            if(check == 0) {
                Toast.makeText(this@LoginActivity, "ID not found", Toast.LENGTH_LONG).show()
            }
        }





        // 일치 하는거 없으면 Toast 띄우기

        // 일치 하는거 있으면 Login 객체 연결시켜주고 Toast(welcome) & finish?

        // Signup 창으로 Intent 회원가입 완료하면 돌아와야 하므로 finish()없음
        binding.loginSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.close3.setOnClickListener {
            finish()
        }
    }
}