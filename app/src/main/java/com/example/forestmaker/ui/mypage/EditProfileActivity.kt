package com.example.forestmaker.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.forestmaker.MainActivity
import com.example.forestmaker.R
import com.example.forestmaker.server.RequestToServer
import com.example.forestmaker.server.data.EditMyInfoResponse
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_edit_profile.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileActivity : AppCompatActivity() {
    var user_id = ""
    var nickname = ""
    var pw = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        user_id = intent.getStringExtra("user_id").toString()
        nickname = intent.getStringExtra("user_nickname").toString()
        pw = intent.getStringExtra("user_pw").toString()

        act_edit_profile_btn_back.setOnClickListener {
            finish()
        }

        act_edit_profile_btn_edit.setOnClickListener {

            // 기존 비밀번호 확인
            if (pw != act_edit_profile_edit_prevPwd.text.toString()) {
                Toast.makeText(this, "비밀번호를 확인해주세요. ", Toast.LENGTH_SHORT).show()
            } else {

                if (act_edit_profile_edit_newPwd1.text.toString() == act_edit_profile_edit_newPwd2.text.toString()) {
                    if (!act_edit_profile_edit_nickname.text.isNullOrBlank()) {
                        nickname = act_edit_profile_edit_nickname.text.toString()
                    }
                    pw = act_edit_profile_edit_newPwd1.text.toString()
                    editMyinfo()
                } else {
                    // 새 비밀번호가 다른 경우
                    Toast.makeText(this, "비밀번호를 확인해주세요. ", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

    fun editMyinfo() {

        val editData = JSONObject()
        editData.put("id", user_id)
        editData.put("nickname", nickname)
        editData.put("pw", pw)
        val body = JsonParser.parseString(editData.toString()) as JsonObject

        RequestToServer.service.requestEditMyInfo(body).enqueue(object : Callback<EditMyInfoResponse>{
            override fun onResponse(
                call: Call<EditMyInfoResponse>,
                response: Response<EditMyInfoResponse>
            ) {
                if (response.isSuccessful) {
                    Log.e("edit success", response.body().toString())
                    Toast.makeText(this@EditProfileActivity, "변경이 완료되었습니다. ", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<EditMyInfoResponse>, t: Throwable) {
                Log.e("fail in editmyinfo", t.message.toString())
            }

        })
    }
}