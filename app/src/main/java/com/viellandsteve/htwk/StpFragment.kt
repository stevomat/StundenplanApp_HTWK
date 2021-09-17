package com.viellandsteve.htwk

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.fragment_stp.*
import kotlinx.android.synthetic.main.fragment_stp.view.*
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE
import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread
import kotlin.coroutines.cancellation.CancellationException


class StpFragment : Fragment(R.layout.fragment_stp) {

    val TAG = "StpFragment"

    private lateinit var communicator: Communicator

    var semester : String = ""





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stp, container, false)

        communicator = activity as Communicator

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn_abrufen : Button = view.findViewById(R.id.button_abrufen)

        val edit_sg : EditText = view.findViewById(R.id.editText_sg)
        val edit_pw : EditText = view.findViewById(R.id.editText_plwoche)

        val semester_spinner : Spinner = view.findViewById(R.id.spinner_semester)
        val sg_spinner : Spinner = view.findViewById(R.id.spinner_sg)
        val pw_spinner : Spinner = view.findViewById(R.id.spinner_plwoche)

        val sharedPref = context!!.getSharedPreferences("shared", MODE_PRIVATE)


        btn_abrufen.setOnClickListener {

            if (!edit_sg.text.equals("Seminargruppe") && !edit_pw.text.toString().equals("")) {
                val editor = sharedPref.edit()
                editor.putString("sg",view.editText_sg.text.toString())
                editor.apply()

                val bund = view.editText_sg.text.toString() + ";" + view.editText_plwoche.text.toString() + ";" + semester
                communicator.passDataCom(bund)
            } else {
                Toast.makeText(activity, "Werte einfügen", Toast.LENGTH_LONG).show()
            }


        }

        edit_sg.setText(sharedPref.getString("sg","..."))
        if (edit_sg.text.toString().equals("...")) {
            edit_sg.text.clear()
            sg_spinner.setSelection(0)
        }



        /**
         * Array-Adapter für das DropDown-Menü --> in der strings.xml erweiterbar
         * Seminargruppe
         */

        activity?.let {
            ArrayAdapter.createFromResource(it, R.array.sg_array, android.R.layout.simple_spinner_item).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                sg_spinner.adapter = adapter
            }
        }

        activity?.let {
            ArrayAdapter.createFromResource(it, R.array.semester_array, android.R.layout.simple_spinner_item).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                semester_spinner.adapter = adapter
            }
        }


        sg_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 >= 1) {
                    edit_sg.setText(sg_spinner.getItemAtPosition(p2).toString())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
//        sg_spinner.setSelection(0)

        /**
         * Plannungswochen (PW)
         */

        semester_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                // Auswahl abspeichern
                val editor = sharedPref.edit()
                editor.putInt("sm",p2)
                editor.apply()

                if (p2==0) {
                    semester = "ws"
                    activity?.let {
                        ArrayAdapter.createFromResource(it, R.array.plwocheWS_array, android.R.layout.simple_spinner_item).also { adapter ->
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            pw_spinner.adapter = adapter
                        }
                    }
                    pw_spinner.setSelection(sharedPref.getInt("pw",0))
                } else if (p2 == 1) {
                    semester = "ss"
                    activity?.let {
                        ArrayAdapter.createFromResource(it, R.array.plwocheSS_array, android.R.layout.simple_spinner_item).also { adapter ->
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            pw_spinner.adapter = adapter
                        }
                    }
                    pw_spinner.setSelection(sharedPref.getInt("pw",0))
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        semester_spinner.setSelection(sharedPref.getInt("sm",0))


        edit_pw.isFocusable = false
        pw_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                // Auswahl abspeichern
                val editor = sharedPref.edit()
                editor.putInt("pw", p2)
                editor.apply()

                if (p2==0) {
                    val calendar1 : Calendar = Calendar.getInstance(Locale.GERMAN)
                    val weekOfYear = calendar1.get(Calendar.WEEK_OF_YEAR)

                    calendar1.set(Calendar.WEEK_OF_YEAR,weekOfYear)
                    calendar1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

                    val year1 = calendar1.get(Calendar.YEAR)
                    val month1 = calendar1.get(Calendar.MONTH) + 1
                    val day1 = calendar1.get(Calendar.DAY_OF_MONTH)

                    calendar1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)

                    val year2 = calendar1.get(Calendar.YEAR)
                    val month2 = calendar1.get(Calendar.MONTH) + 1
                    val day2 = calendar1.get(Calendar.DAY_OF_MONTH)

                    textView_range.text = "($day1.$month1.$year1 - $day2.$month2.$year2)"

                    if (weekOfYear in 35..61) {
                        semester_spinner.setSelection(0)
                    }
                    if (weekOfYear in 11..35) {
                        semester_spinner.setSelection(1)
                    }

                    edit_pw.setText(weekOfYear.toString()+". PW")
                } else if (p2 >= 2) {
                    val calendar : Calendar = Calendar.getInstance(Locale.GERMAN)
                    val auswahlpw = pw_spinner.getItemAtPosition(p2).toString().take(2)

                    calendar.set(Calendar.WEEK_OF_YEAR,auswahlpw.toInt())
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

                    val year1 = calendar.get(Calendar.YEAR)
                    val month1 = calendar.get(Calendar.MONTH) + 1
                    val day1 = calendar.get(Calendar.DAY_OF_MONTH)

                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)

                    val year2 = calendar.get(Calendar.YEAR)
                    val month2 = calendar.get(Calendar.MONTH) + 1
                    val day2 = calendar.get(Calendar.DAY_OF_MONTH)

                    edit_pw.setText(pw_spinner.getItemAtPosition(p2).toString())

                    textView_range.text = "($day1.$month1.$year1 - $day2.$month2.$year2)"


                } else if (p2 == 1) {
                    if (semester.equals("ws")) {
                        edit_pw.setText("35-61.")
                    } else if (semester.equals("ss")) {
                        edit_pw.setText("11-35.")
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}