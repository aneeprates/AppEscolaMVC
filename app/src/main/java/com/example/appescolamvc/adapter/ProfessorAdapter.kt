package com.example.appescolamvc.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appescolamvc.activity.CadastroProfessorActivity
import com.example.appescolamvc.databinding.ActivityItemProfessorBinding
import com.example.appescolamvc.model.Professor

class ProfessorAdapter (
    private val context: Context,
    private val deleteCallback: (Int) -> Unit
    ) : RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder>() {
    private var professor: List<Professor> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessorViewHolder {
        val binding =
            ActivityItemProfessorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ProfessorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfessorAdapter.ProfessorViewHolder, position: Int) {
        val professor = professor[position]
        holder.bind(professor)

        holder.binding.btnDeletarProf.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Excluir professor")
                .setMessage("Deseja realmente excluir o professor ${professor.nome}?")
                .setPositiveButton("Sim") { _, _ ->
                    deleteCallback(professor.id)
                }
                .setNegativeButton("Não", null)
                .show()
        }

        holder.binding.btnEditarProf.setOnClickListener {
            val intent = Intent(context, CadastroProfessorActivity::class.java)
            intent.putExtra("PROFESSOR_ID", professor.id)
            intent.putExtra("PROFESSOR_NOME", professor.nome)
            intent.putExtra("PROFESSOR_CPF", professor.cpf)
            intent.putExtra("PROFESSOR_EMAIL", professor.email)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return professor.size
    }

    fun setData(professor:  List<Professor>) {
        this.professor = professor
        notifyDataSetChanged()
    }

    inner class ProfessorViewHolder(val binding: ActivityItemProfessorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(professor: Professor) {
            binding.apply {
                txtNomeProf.text = professor.nome
                txtCpfProf.text = professor.cpf
                txtEmailProf.text = professor.email
                txtMatriculaProf.text = professor.matricula
            }
        }
    }
}

