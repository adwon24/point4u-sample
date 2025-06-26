package com.point4u.sample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adwon.point4u.Point4U

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<AppCompatButton>(R.id.btn_point4u).setOnClickListener {
            startPoint4U()
        }
    }

    private fun startPoint4U() {
        val point4u = Point4U()
        point4u.startPoint4U(
            activity = this@MainActivity,
            companyCode = POINT4U_TEST_COMPANY_CODE,
            sdkCode = POINT4U_TEST_SDK_CODE,
            memberId = POINT4U_TEST_MEMBER_ID,
            age = POINT4U_TEST_MEMBER_AGE,
            gender = POINT4U_TEST_MEMBER_GENDER
        )
    }


    companion object {
        const val POINT4U_TEST_COMPANY_CODE = "2e72dbd2-e246-4d4f-9d29-472712232c72"
        const val POINT4U_TEST_SDK_CODE = "78dbdded-f2be-469a-8444-79a8b0334c97"
        const val POINT4U_TEST_MEMBER_ID = "sample_1234"

        const val POINT4U_TEST_MEMBER_AGE = "28"
        const val POINT4U_TEST_MEMBER_GENDER = "1" // 남성 1, 여성 0
    }
}