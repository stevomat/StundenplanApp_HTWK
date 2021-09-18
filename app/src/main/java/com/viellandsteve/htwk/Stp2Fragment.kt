package com.viellandsteve.htwk


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_stp.*
import org.jsoup.Jsoup
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread


class Stp2Fragment : Fragment(R.layout.fragment_stp2) {
    val TAG = "Stp2Fragment"
    var displayMessage : String? = ""
    var SG : String = ""
    var PW : String = ""
    var semester : String = ""

    var URLstp = ""

    var rowcounter : Int = 0
    var index_wochentag : Int = 0

    val listen : MutableLiveData<String> = MutableLiveData()

    val list_montag : MutableList<Array<String>> = ArrayList()
    val list_dienstag : MutableList<Array<String>> = ArrayList()
    val list_mittwoch : MutableList<Array<String>> = ArrayList()
    val list_donnerstag : MutableList<Array<String>> = ArrayList()
    val list_freitag : MutableList<Array<String>> = ArrayList()
    val list_samstag : MutableList<Array<String>> = ArrayList()
    val list_sonntag : MutableList<Array<String>> = ArrayList()

    var exc_flag = 0





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stp2, container, false)

        displayMessage = arguments?.getString("message")

        // SG und PW splitten aus dem Paket displayMessage
        val split = displayMessage?.split(";")
        SG = (split?.get(0) ?: String) as String
        semester = (split?.get(2) ?: String) as String

        val pwsplit = split?.get(1)?.split(".")
        PW = (pwsplit?.get(0) ?: String) as String

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wochentag_spinner : Spinner = view.findViewById(R.id.spinner_wochentag)

        val text_pw : TextView = view.findViewById(R.id.textView_pw)
        val text_sg : TextView = view.findViewById(R.id.textView_sg)
        text_sg.setText(SG)

        val calendar : Calendar = Calendar.getInstance(Locale.GERMAN)

        if (!PW.equals("35-61") && !PW.equals("11-35")) {
            calendar.set(Calendar.WEEK_OF_YEAR,PW.toInt())
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

            val year1 = calendar.get(Calendar.YEAR)
            val month1 = calendar.get(Calendar.MONTH) + 1
            val day1 = calendar.get(Calendar.DAY_OF_MONTH)

            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)

            val year2 = calendar.get(Calendar.YEAR)
            val month2 = calendar.get(Calendar.MONTH) + 1
            val day2 = calendar.get(Calendar.DAY_OF_MONTH)

            text_pw.setText("($day1.$month1.$year1 - $day2.$month2.$year2)")
        } else {
            text_pw.setText(PW + ". Woche")
        }




        URLstp = constructUrl(SG,PW,semester)
        retrieveWebInfo(URLstp)

        val listView = view.findViewById<ListView>(R.id.listview_stp)

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val element = adapterView.getItemAtPosition(i)
            Log.i(TAG, "element: " + element)
        }

        listen.observe(this, Observer {
            listView.adapter = CostumAdapter(requireContext(), rowcounter, index_wochentag, list_montag, list_dienstag, list_mittwoch, list_donnerstag, list_freitag, list_samstag, list_sonntag)
        })

        // Spinner-Wochentag
        activity?.let {
            ArrayAdapter.createFromResource(it, R.array.wochentage_array, android.R.layout.simple_spinner_item).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                wochentag_spinner.adapter = adapter
            }
        }

        wochentag_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                index_wochentag = p2
                listen.value = "Changed Value"
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }



    /**
     * Extrahieren der Webdaten aus dem HTWK-Stundenplan
     */

    private fun constructUrl(sg: String, pw: String, sm:String): String {
        val template = "https://stundenplan.htwk-leipzig.de/"+ sm + "/Berichte/Text-Listen;Studenten-Sets;name;"+ sg + "?template=sws_semgrp_js&weeks=" + pw
        return template
    }

    private fun retrieveWebInfo(URLstp: String) {

        thread{
            try {
                exc_flag = 0
                val doc = Jsoup.connect(URLstp).get()

                val tableelements = doc.getElementsByClass("spreadsheet")

                for(i in 0..6) {

                    val table = tableelements[i] // 0 --> Montag, 1 --> Dienstag
                    val rows = table.select("tr")
                    rowcounter = rows.size - 1

                    Log.i(TAG, "output: " + tableelements.text())

                    if (rows.size != 0) {

//                    val columnNames =  rows.get(0) // Spaltennamen
//                    var columnContent =  rows.get(1) // Spalteninhalte

                        // Inhalte indexieren
//                    var cContent = columnContent.getElementsByTag("td")
//                    val cNames = columnNames.getElementsByTag("td")


                        for (veranstaltung in 1..rowcounter) {
                            val columnContent = rows.get(veranstaltung)
                            val cContent = columnContent.getElementsByTag("td")

                            // Montag
                            if (i == 0) {
                                val array_montag = Array<String>(9) { "0"}
                                for (j in 0..7) {
                                    array_montag[j] = cContent[j].text()
                                }
                                array_montag[8] = rowcounter.toString()
                                list_montag.add(array_montag)

                                Log.i(TAG, "rows Montag: " + list_montag.get(0)[7]) // !!
                            }
                            // Dienstag
                            if (i == 1) {
                                val array_dienstag = Array<String>(9) { "0"}
                                for (k in 0..7) {
                                    array_dienstag[k] = cContent[k].text()
                                }
                                array_dienstag[8] = rowcounter.toString()
                                list_dienstag.add(array_dienstag)
                                Log.i(TAG, "rows Dienstag: " + list_dienstag)
                            }
                            // Mittwoch
                            if (i == 2) {
                                val array_mittwoch = Array<String>(9) { "0"}
                                for (k in 0..7) {
                                    array_mittwoch[k] = cContent[k].text()
                                }
                                array_mittwoch[8] = rowcounter.toString()
                                list_mittwoch.add(array_mittwoch)
                                Log.i(TAG, "rows Mittwoch: " + list_mittwoch)
                            }
                            // Donnerstag
                            if (i == 3) {
                                val array_donnerstag = Array<String>(9) { "0"}
                                for (k in 0..7) {
                                    array_donnerstag[k] = cContent[k].text()
                                }
                                array_donnerstag[8] = rowcounter.toString()
                                list_donnerstag.add(array_donnerstag)
                                Log.i(TAG, "rows Donnerstag: " + list_donnerstag)
                            }
                            // Freitag
                            if (i == 4) {
                                val array_freitag = Array<String>(9) { "0"}
                                for (k in 0..7) {
                                    array_freitag[k] = cContent[k].text()
                                }
                                array_freitag[8] = rowcounter.toString()
                                list_freitag.add(array_freitag)
                                Log.i(TAG, "rows Freitag: " + list_freitag)
                            }
                            // Samstag
                            if (i == 5) {
                                val array_samstag = Array<String>(9) { "0"}
                                for (k in 0..7) {
                                    array_samstag[k] = cContent[k].text()
                                }
                                array_samstag[8] = rowcounter.toString()
                                list_samstag.add(array_samstag)
                                Log.i(TAG, "rows Samstag: " + list_samstag)
                            }
                            // Sonntag
                            if (i == 6) {
                                val array_sonntag = Array<String>(9) { "0"}
                                for (k in 0..7) {
                                    array_sonntag[k] = cContent[k].text()
                                }
                                array_sonntag[8] = rowcounter.toString()
                                list_sonntag.add(array_sonntag)
                                Log.i(TAG, "rows Sonntag: " + list_sonntag)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                exc_flag = 1
                Log.i(TAG, "exeption: " + e)
            }
                activity?.runOnUiThread {

                    if (exc_flag == 0) {
                        // Change-Listener: Auslöser
                        listen.value = "Changed Value"
                    } else if (exc_flag == 1){
                        Toast.makeText(activity, "Kein Stundeplan für $SG verfügbar", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

}
