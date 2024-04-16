package com.example.leilao.ui.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leilao.databinding.ItemLeilaoBinding
import com.example.leilao.model.Leilao

class LeilaoAdapter : RecyclerView.Adapter<LeilaoAdapter.ViewHolder>() {

    private var leiloes = arrayListOf<Leilao>()
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemLeilaoBinding.inflate(LayoutInflater
            .from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val leilao = leiloes[position]

        holder.tvDescricao.text = leilao.descricao
        holder.tvMaiorLance.text = String.format("R$ %.2f", leilao.maiorLance ?: 0.0)

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, leilao)
            }
        }
    }

    override fun getItemCount(): Int {

        return leiloes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(leiloes: List<Leilao>) {
        this.leiloes.clear()
        this.leiloes.addAll(leiloes)
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: Leilao)
    }

    class ViewHolder(view: ItemLeilaoBinding) : RecyclerView.ViewHolder(view.root) {
        val tvDescricao = view.itemLeilaoDescricao
        val tvMaiorLance = view.itemLeilaoMaiorLance
        val ivImage = view.itemLeilaoImagem
    }
}