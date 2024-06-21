package com.example.appescolamvc.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appescolamvc.api.EnderecoAPI3
import com.example.appescolamvc.api.RetrofitHelper
import com.example.appescolamvc.databinding.ActivityCadastroTurmaBinding
import com.example.appescolamvc.model.Turma
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CadastroTurmaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroTurmaBinding
    private var turmaId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroTurmaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        turmaId = intent.getIntExtra("TURMA_ID", -1)
        if (turmaId != -1) {
            binding.edtCurso.setText(intent.getStringExtra("TURMA_CURSO"))
            binding.edtMatriculaTurma.setText(intent.getStringExtra("TURMA_MATRICULA"))
        }

        binding.btnSaveTurma.setOnClickListener {
            val curso = binding.edtCurso.text.toString()
            val matricula = binding.edtMatriculaTurma.text.toString()

            if (curso.isNotEmpty() && matricula.isNotEmpty()) {
                val turma = Turma(turmaId ?: 0, curso, matricula)
                if (turmaId != null && turmaId != -1) {
                    alterarTurma(turma)
                } else {
                    salvarTurma(turma)
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun salvarTurma(turma: Turma) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoAPI3::class.java)
        val call = service.inserirTurma(turma)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    setResult(Activity.RESULT_OK)
                    finish()
                } else {
                    Toast.makeText(this@CadastroTurmaActivity, "Erro ao salvar turma.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@CadastroTurmaActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun alterarTurma(turma: Turma) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoAPI3::class.java)
        val call = service.alterarTurma(turma)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    setResult(Activity.RESULT_OK, Intent().putExtra("TURMA_ALTERADO", true))
                    finish()
                } else {
                    Toast.makeText(this@CadastroTurmaActivity, "Erro ao alterar turma.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@CadastroTurmaActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}