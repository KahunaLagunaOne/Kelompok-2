package tsm.bdg.ch6group

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tsm.bdg.ch6group.databinding.ItemRvHistoryBinding

class RvAdapter(private val listData: MutableList<Game>) :
    RecyclerView.Adapter<RvAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(var binding: ItemRvHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = ItemRvHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.binding.tvStatusItemRvHistory.text = listData[position].status
        holder.binding.tvDateItemRvHistory.text = listData[position].date
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}
