package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.sms.R
import kotlinx.android.synthetic.main.item_onboarding.view.*
import models_class.DataOnBoarding

class PagerListAdapter(var list: List<DataOnBoarding>) : RecyclerView.Adapter<PagerListAdapter
.Vh>() {
    inner class Vh(var itemRv: View) : RecyclerView.ViewHolder(itemRv) {
        fun onBind(image: DataOnBoarding) {
            itemRv.onBoard_image.setImageResource(image.image)
            itemRv.onBoard_title.text = image.title
            itemRv.onBoard_about.text = image.about
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            LayoutInflater.from(parent.context).inflate(R.layout.item_onboarding, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}