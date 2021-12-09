package com.my.Day4BookFilter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.my.day4taskbook.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val authorDetails=findViewById<TextInputLayout>(R.id.authorView).editText?.text
        val countryDetails=findViewById<TextInputLayout>(R.id.countryView).editText?.text
        val clickButton=findViewById<Button>(R.id.clickButton)
        val search=findViewById<TextView>(R.id.showData)

        clickButton.setOnClickListener()
        {
            var count=0
            val myApplication=application as BaseUrlForBookFilter
            val httpApiService=myApplication.httpApiService

            CoroutineScope(Dispatchers.IO).launch {
                val decodedSolution=httpApiService.getBookDetails()
                withContext(Dispatchers.Main)
                {
                    val myBuilder=StringBuilder()
                    for(myInfo in decodedSolution){
                        if(count<3) {
                            if (authorDetails.toString() == myInfo.author && countryDetails.toString() == myInfo.country) {
                                myBuilder.append("Result : "+myInfo.title)
                                myBuilder.append("\n")
                                count++
                            }

                        }

                    }
                    search.text= "Results : "+count+"\n$myBuilder"

                }
            }
        }
    }
}
