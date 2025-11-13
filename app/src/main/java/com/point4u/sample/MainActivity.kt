package com.point4u.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
            WindowInsetsCompat.CONSUMED
            //insets
        }

        findViewById<AppCompatButton>(R.id.btn_point4u).setOnClickListener {
            startPoint4U()
        }

        findViewById<AppCompatButton>(R.id.btn_point4u_step_list).setOnClickListener {
            getStepList()
        }
    }

    /**
     * point4u 시작 함수 테스트
     */
    private fun startPoint4U() {
        val point4u = Point4U()
        point4u.setPrimaryColor(this@MainActivity, "#ff749bfa") // 선택 사항 - 매체사의 primary color 값 세팅
        point4u.setPrimaryLogoColor(this@MainActivity, "#ff749bfa") // 선택 사항 - 매체사의 primary color 값 세팅
        point4u.startPoint4U(
            activity = this@MainActivity,
            companyCode = POINT4U_TEST_COMPANY_CODE,
            sdkCode = POINT4U_TEST_SDK_CODE,
            memberId = POINT4U_TEST_MEMBER_ID,
            ageOrBirth = POINT4U_TEST_MEMBER_AGE,
            gender = POINT4U_TEST_MEMBER_GENDER
        )
    }

    /**
     * point4u 걸음 통계 데이터 반환 함수 테스트
     */
    private fun getStepList() {
        val point4u = Point4U()
        point4u.getStepCountList(this@MainActivity, startDate = "2025-11-01", endDate = "2025-11-13", object : Point4U.OnPoint4UStepListener {
            override fun onPoint4UStepCountListResponse(stepCountList: ArrayList<Pair<String, Int>>?) {
                Log.d("adwon", "onPoint4UStepCountListResponse : $stepCountList")
            }
        })
    }

    companion object {
        const val POINT4U_TEST_COMPANY_CODE = "2e72dbd2-e246-4d4f-9d29-472712232c72"
        const val POINT4U_TEST_SDK_CODE = "78dbdded-f2be-469a-8444-79a8b0334c97"
        const val POINT4U_TEST_MEMBER_ID = "sample_1234"

        const val POINT4U_TEST_MEMBER_AGE = "28"
        const val POINT4U_TEST_MEMBER_GENDER = "1" // 남성 1, 여성 0
    }
}