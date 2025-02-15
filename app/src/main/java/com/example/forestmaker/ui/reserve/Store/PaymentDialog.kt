package com.example.forestmaker.ui.reserve.Store

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.TextView
import com.example.forestmaker.R

class PaymentDialog(context : Context) {
    private val dlg = Dialog(context)   //부모 액티비티의 context 가 들어감
    private lateinit var btnGo : TextView
    private lateinit var btnClose : TextView
    private lateinit var listener : PaymentDialogClickedListener

    fun start() {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(R.layout.dialog_payment)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함
        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnGo = dlg.findViewById(R.id.dialog_payment_goReserveInfo)
        btnGo.setOnClickListener {

            listener.onGOClicked("GO")
            dlg.dismiss()
        }

        btnClose = dlg.findViewById(R.id.dialog_payment_close)
        btnClose.setOnClickListener {
            listener.onCLOSEClicked("CLOSE")
            dlg.dismiss()
        }

        dlg.show()
    }

    fun setOnClickedListener(listener: (String) -> Unit) {
        this.listener = object: PaymentDialogClickedListener {
            override fun onGOClicked(content: String) {
                listener(content)
            }

            override fun onCLOSEClicked(content: String) {
                listener(content)
            }
        }
    }

    interface PaymentDialogClickedListener {
        fun onGOClicked(content : String)
        fun onCLOSEClicked(content : String)
    }

}