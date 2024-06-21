package com.example.appescolamvc.activity

import EnderecoAPI2
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appescolamvc.api.RetrofitHelper
import com.example.appescolamvc.databinding.ActivityCadastroProfessorBinding
import com.example.appescolamvc.model.Professor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroProfessorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroProfessorBinding
    private var professorId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroProfessorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        professorId = intent.getIntExtra("PROFESSOR_ID", -1)
        if (professorId != -1) {
            binding.edtNomeProf.setText(intent.getStringExtra("PROFESSOR_NOME"))
            binding.edtCpfProf.setText(intent.getStringExtra("PROFESSOR_CPF"))
            binding.edtEmailProf.setText(intent.getStringExtra("PROFESSOR_EMAIL"))
            binding.edtMatriculaProf.setText(intent.getStringExtra("PROFESSOR_MATRICULA"))
        }

        binding.btnSaveProf.setOnClickListener {
            val nome = binding.edtNomeProf.text.toString()
            val cpf = binding.edtCpfProf.text.toString()
            val email = binding.edtEmailProf.text.toString()
            val matricula = binding.edtMatriculaProf.text.toString()

            if (nome.isNotEmpty() && cpf.isNotEmpty() && email.isNotEmpty() && matricula.isNotEmpty()) {
                val professor = Professor(professorId ?: 0, nome, cpf, email, matricula)
                if (professorId != null && professorId != -1) {
                    alterarProfessor(professor)
                } else {
                    salvarProfessor(professor)
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun salvarProfessor(professor: Professor) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoAPI2::class.java)
        val call = service.inserirProfessor(professor)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    setResult(Activity.RESULT_OK)
                    finish()
                } else {
                    Toast.makeText(this@CadastroProfessorActivity, "Erro ao salvar professor.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@CadastroProfessorActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun alterarProfessor(professor: Professor) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoAPI2::class.java)
        val call = service.alterarProfessor(professor)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    setResult(Activity.RESULT_OK, Intent().putExtra("PROFESSOR_ALTERADO", true))
                    finish()
                } else {
                    Toast.makeText(this@CadastroProfessorActivity, "Erro ao alterar professor.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@CadastroProfessorActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


