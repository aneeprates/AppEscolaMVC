package com.example.appescolamvc.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appescolamvc.activity.CadastroTurmaActivity
import com.example.appescolamvc.databinding.ActivityItemTurmaBinding
import com.example.appescolamvc.model.Turma

class TurmaAdapter (
    private val context: Context,
    private val deleteCallback: (Int) -> Unit
    ) : RecyclerView.Adapter<TurmaAdapter.TurmaViewHolder>() {
    private var turma: List<Turma> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TurmaViewHolder {
        val binding =
            ActivityItemTurmaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return TurmaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TurmaViewHolder, position: Int) {
        val turma = turma[position]
        holder.bind(turma)

        holder.binding.btnDeletarTurma.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Excluir Turma")
                .setMessage("Deseja realmente excluir a turma ${turma.curso}?")
                .setPositiveButton("Sim") { _, _ ->
                    deleteCallback(turma.id)
                }
                .setNegativeButton("Não", null)
                .show()
        }

        holder.binding.btnEditarTurma.setOnClickListener {
            val intent = Intent(context, CadastroTurmaActivity::class.java)
            intent.putExtra("TURMA_CURSO", turma.curso)
            intent.putExtra("TURMA_MATRICULA", turma.matricula)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return turma.size
    }

    fun setData(turma: List<Turma>) {
        this.turma = turma
        notifyDataSetChanged()
    }

    inner class TurmaViewHolder(val binding: ActivityItemTurmaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(turma: Turma) {
            binding.apply {
                txtCurso.text = turma.curso
                txtMatriculaTurma.text = turma.matricula
            }
        }
    }
}
