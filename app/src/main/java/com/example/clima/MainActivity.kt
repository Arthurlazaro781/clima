package com.example.clima

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Conectando o código aos elementos da tela que criamos no XML
        val etCityName = findViewById<EditText>(R.id.etCityName)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // O que acontece quando o botão é clicado
        btnSearch.setOnClickListener {
            val cidade = etCityName.text.toString().trim()

            // Requisito obrigatório: Validar campo vazio
            if (cidade.isEmpty()) {
                Toast.makeText(this, "Por favor, digite o nome de uma cidade.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            tvResult.text = "Buscando o clima..."

            // Requisito obrigatório: Consumir API Pública (wttr.in)
            // Trocamos espaços em branco por %20 para a URL não quebrar
            val url = "https://wttr.in/${cidade.replace(" ", "%20")}?format=j1"

            val queue = Volley.newRequestQueue(this)

            val request = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    try {
                        // Navegando no JSON para pegar os dados
                        val current = response.getJSONArray("current_condition").getJSONObject(0)
                        val temp = current.getString("temp_C")
                        val umidade = current.getString("humidity")
                        val vento = current.getString("windspeedKmph")

                        // Requisito obrigatório: Exibir pelo menos 3 informações úteis
                        val resultado = """
                            Temperatura: $temp °C
                            Umidade: $umidade%
                            Vento: $vento km/h
                        """.trimIndent()

                        tvResult.text = resultado
                    } catch (e: Exception) {
                        tvResult.text = "Erro ao processar os dados do clima."
                    }
                },
                { erro ->
                    // Requisito obrigatório: Tratar erro de requisição ou cidade inválida
                    tvResult.text = "Cidade não encontrada ou sem internet."
                })

            queue.add(request)
        }
    }
}