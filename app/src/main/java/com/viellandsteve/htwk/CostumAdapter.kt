package com.viellandsteve.htwk

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.row_stp.view.*

class CostumAdapter(context: Context, rowcounter: Int, index_wochentag: Int, list_Montag: MutableList<Array<String>>, list_Dienstag: MutableList<Array<String>>, list_Mittwoch: MutableList<Array<String>>, list_Donnerstag: MutableList<Array<String>>, list_Freitag: MutableList<Array<String>>, list_Samstag: MutableList<Array<String>>, list_Sonntag: MutableList<Array<String>>) : BaseAdapter() {
    val TAG = "CostumAdapter"
    // Parameter initialisieren
    private val mContext : Context

    var rowcount = 0
    val index_wt = index_wochentag
    val list_mt : MutableList<Array<String>> = list_Montag
    val list_dt : MutableList<Array<String>> = list_Dienstag
    val list_mit : MutableList<Array<String>> = list_Mittwoch
    val list_don : MutableList<Array<String>> = list_Donnerstag
    val list_ft : MutableList<Array<String>> = list_Freitag
    val list_sa : MutableList<Array<String>> = list_Samstag
    val list_so : MutableList<Array<String>> = list_Sonntag

    init {
        mContext = context
    }

    // Anzahl der Zeilen in der Liste
    override fun getCount(): Int {
        if (index_wt == 0) { // Montag
            if (!list_mt.isNullOrEmpty()) {
                rowcount = list_mt.get(0)[8].toInt()
            }
        }
        if (index_wt == 1) { // Dienstag
            if (!list_dt.isNullOrEmpty()) {
                rowcount = list_dt.get(0)[8].toInt()
            }
        }
        if (index_wt == 2) { // Mittwoch
            if (!list_mit.isNullOrEmpty()) {
                rowcount = list_mit.get(0)[8].toInt()
            }
        }

        if (index_wt == 3) { // Donnerstag
            if (!list_don.isNullOrEmpty()) {
                rowcount = list_don.get(0)[8].toInt()
            }
        }

        if (index_wt == 4) { // Freitag
            if (!list_ft.isNullOrEmpty()) {
                rowcount = list_ft.get(0)[8].toInt()
            }
        }
        if (index_wt == 5) { // Samstag
            if (!list_sa.isNullOrEmpty()) {
                rowcount = list_sa.get(0)[8].toInt()
            }
        }
        if (index_wt ==6) { // Sonntag
            if (!list_so.isNullOrEmpty()) {
                rowcount = list_so.get(0)[8].toInt()
            }
        }
        return rowcount
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getItem(p0: Int): Any {
        return "TEST STRING"
    }

    // render für jede Zeile
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val row_stp = layoutInflater.inflate(R.layout.row_stp, p2, false)

        if (index_wt==0) { // Montag
            if (!list_mt.isNullOrEmpty()) {
                row_stp.textView_pw_row.text = list_mt.get(p0)[0]
                row_stp.textView_uhrzeit.text = list_mt.get(p0)[1] + " - " + list_mt.get(p0)[2]
                row_stp.textView_veranstaltung.text = list_mt.get(p0)[3]
                row_stp.textView_art.text = list_mt.get(p0)[4]
                row_stp.textView_professor.text = list_mt.get(p0)[5]
                row_stp.textView_raum.text = list_mt.get(p0)[6]
                // 'Bermerkungen?'
            }
        }

        if (index_wt==1) { // Dienstag
            if (!list_dt.isNullOrEmpty()) {
                row_stp.textView_pw_row.text = list_dt.get(p0)[0]
                row_stp.textView_uhrzeit.text = list_dt.get(p0)[1] + " - " + list_dt.get(p0)[2]
                row_stp.textView_veranstaltung.text = list_dt.get(p0)[3]
                row_stp.textView_art.text = list_dt.get(p0)[4]
                row_stp.textView_professor.text = list_dt.get(p0)[5]
                row_stp.textView_raum.text = list_dt.get(p0)[6]
            }
        }

        if (index_wt==2) { // Mittwoch
            if (!list_mit.isNullOrEmpty()) {
                row_stp.textView_pw_row.text = list_mit.get(p0)[0]
                row_stp.textView_uhrzeit.text = list_mit.get(p0)[1] + " - " + list_mit.get(p0)[2]
                row_stp.textView_veranstaltung.text = list_mit.get(p0)[3]
                row_stp.textView_art.text = list_mit.get(p0)[4]
                row_stp.textView_professor.text = list_mit.get(p0)[5]
                row_stp.textView_raum.text = list_mit.get(p0)[6]
            }
        }

        if (index_wt==3) { // Donnerstag
            if (!list_don.isNullOrEmpty()) {
                row_stp.textView_pw_row.text = list_don.get(p0)[0]
                row_stp.textView_uhrzeit.text = list_don.get(p0)[1] + " - " + list_don.get(p0)[2]
                row_stp.textView_veranstaltung.text = list_don.get(p0)[3]
                row_stp.textView_art.text = list_don.get(p0)[4]
                row_stp.textView_professor.text = list_don.get(p0)[5]
                row_stp.textView_raum.text = list_don.get(p0)[6]
            }
        }

        if (index_wt==4) { // Freitag
            if (!list_ft.isNullOrEmpty()) {
                row_stp.textView_pw_row.text = list_ft.get(p0)[0]
                row_stp.textView_uhrzeit.text = list_ft.get(p0)[1] + " - " + list_ft.get(p0)[2]
                row_stp.textView_veranstaltung.text = list_ft.get(p0)[3]
                row_stp.textView_art.text = list_ft.get(p0)[4]
                row_stp.textView_professor.text = list_ft.get(p0)[5]
                row_stp.textView_raum.text = list_ft.get(p0)[6]
            }
        }

        if (index_wt==5) { // Samstag
            if (!list_sa.isNullOrEmpty()) {
                row_stp.textView_pw_row.text = list_sa.get(p0)[0]
                row_stp.textView_uhrzeit.text = list_sa.get(p0)[1] + " - " + list_sa.get(p0)[2]
                row_stp.textView_veranstaltung.text = list_sa.get(p0)[3]
                row_stp.textView_art.text = list_sa.get(p0)[4]
                row_stp.textView_professor.text = list_sa.get(p0)[5]
                row_stp.textView_raum.text = list_sa.get(p0)[6]
            }
        }

        if (index_wt==6) { // Sonntag
            if (!list_so.isNullOrEmpty()) {
                row_stp.textView_pw_row.text = list_so.get(p0)[0]
                row_stp.textView_uhrzeit.text = list_so.get(p0)[1] + " - " + list_so.get(p0)[2]
                row_stp.textView_veranstaltung.text = list_so.get(p0)[3]
                row_stp.textView_art.text = list_so.get(p0)[4]
                row_stp.textView_professor.text = list_so.get(p0)[5]
                row_stp.textView_raum.text = list_so.get(p0)[6]
            }
        }

        return row_stp
    }


}