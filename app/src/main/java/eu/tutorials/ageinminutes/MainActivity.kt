package eu.tutorials.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            clickDatePicker(view)

        }
    }

    fun clickDatePicker(view: View) {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,
                    "The chosen year is $selectedYear, the month is ${selectedMonth+1} and day is $selectedDayOfMonth", Toast.LENGTH_LONG).show()
                val selectedDay="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate.setText(selectedDay)
                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate=sdf.parse(selectedDay)

                val selectedDateInMinutes=theDate!!.time/60000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMinutes=currentDate!!.time/60000

                val differnceInMinutes=currentDateToMinutes-selectedDateInMinutes
                tvSelectedDateInMinutes.setText(differnceInMinutes.toString())

                val differenceAgeInYears:Double=Math.floor((differnceInMinutes/(60*24*(365.2422))))
                val differenceAgeInDays:Double=Math.floor((differnceInMinutes%(60*24*(365.2422)))/(24*60))
                val k="$differenceAgeInYears years , $differenceAgeInDays days old"
                tvSelectedDateYearsOld.setText(k)



            }
            , year
            , month
            , day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }
}
