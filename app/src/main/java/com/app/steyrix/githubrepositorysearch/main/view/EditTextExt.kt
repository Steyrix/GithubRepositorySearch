package com.app.steyrix.githubrepositorysearch.main.view

import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onTextChanged(onTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        var timer: CountDownTimer? = null

        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            timer?.cancel()
            timer = object : CountDownTimer(300, 1500) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    onTextChanged.invoke(s.toString())
                }
            }.start()
        }
    })
}