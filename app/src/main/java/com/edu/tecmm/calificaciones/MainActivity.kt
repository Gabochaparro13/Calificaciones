package com.edu.tecmm.calificaciones

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editCalificacion: EditText
    private lateinit var btnCalificar: Button
    private lateinit var txtResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editCalificacion= findViewById(R.id.editCalificacion)
        btnCalificar = findViewById(R.id.btnCalificar)
        txtResultado = findViewById(R.id.txtResultado)

        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val ultimaCalificacion = sharedPreferences.getString("ultimaCalificacion", "")
        editCalificacion.setText(ultimaCalificacion)

        btnCalificar.setOnClickListener {
            val calificacionStr = editCalificacion.text.toString()
            if (calificacionStr.isNotEmpty()) {
                val calificacion = calificacionStr.toInt()

                val resultado = when {
                    calificacion < 70 -> "Reprobado"
                    calificacion in 70..90 -> "Aprobado"
                    else -> "Excelente"
                }

                txtResultado.text = resultado

                with(sharedPreferences.edit()) {
                    putString("ultimaCalificacion", calificacionStr)
                    apply()
                }
            }
        }
    }
}