package basic_Calculator.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

import kotlin.ArithmeticException

class MainActivity : AppCompatActivity() {
    // Toast.makeText(this,"Button Clicked",Toast.LENGTH_SHORT).show()

    private  var text1: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text1 = findViewById(R.id.tv1Id)
    }

    fun onDig(view: View){
        text1?.append((view as Button).text)
        lastNumeric = true
    }
    fun onClr(view: View){
        text1?.text = ""
    }
    fun onDot(view: View){
        if(lastNumeric && !lastDot){
            text1?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun oprClick(view: View){
        text1?.text?.let {
            if(lastNumeric && !isOpAdd(it.toString())){
                text1?.append((view as Button).text)
                lastNumeric = true
                lastDot = false
            }
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var textV = text1?.text.toString()
            var prefix = ""
            try{
                if(textV.startsWith("-")){
                    prefix = "-"
                    textV = textV.substring(1)
                }
                if(textV.contains("-")){
                    val splitVal = textV.split("-")

                    var a = splitVal[0]
                    var b = splitVal[1]

                    if(prefix.isNotEmpty()){
                        a = prefix + a
                    }

                    var result = a.toDouble()-b.toDouble()
                    text1?.text = result.toString()
                }else if(textV.contains("+")){
                    val splitVal = textV.split("+")

                    var a = splitVal[0]
                    var b = splitVal[1]

                    if(prefix.isNotEmpty()){
                        a = prefix + a
                    }

                    var result = a.toDouble() + b.toDouble()
                    text1?.text = result.toString()
                }else if(textV.contains("*")){
                    val splitVal = textV.split("*")

                    var a = splitVal[0]
                    var b = splitVal[1]

                    if(prefix.isNotEmpty()){
                        a = prefix + a
                    }

                    var result = a.toDouble() * b.toDouble()
                    text1?.text = result.toString()
                }else if(textV.contains("/")){
                    val splitVal = textV.split("/")

                    var a = splitVal[0]
                    var b = splitVal[1]

                    if(prefix.isNotEmpty()){
                        a = prefix + a
                    }

                    var result = a.toDouble() / b.toDouble()
                    text1?.text = result.toString()
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private  fun isOpAdd(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/")
                    || value.contains("+")
                    || value.contains("*")
                    || value.contains("-")
        }
    }
}