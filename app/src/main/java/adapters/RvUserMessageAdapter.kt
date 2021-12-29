package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.sms.R
import com.developer.sms.databinding.ItemUsersListBinding
import com.squareup.picasso.Picasso
import models_class.Users

class RvUserMessageAdapter(val list: List<Users>, val click: Click) :
    RecyclerView.Adapter<RvUserMessageAdapter.Vh>() {


    inner class Vh(var itemRv: ItemUsersListBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(user: Users) {

            if (user.imageUrl == null) {
                itemRv.circleImage.setImageResource(R.drawable.ic_account)
            } else {
                Picasso.get().load(user.imageUrl).into(itemRv.circleImage)
            }

            if (user.name == null) {
                itemRv.name.text = user.idToken
            } else {
                itemRv.name.text = user.name
            }

            if (user.newMessage != "") {
                itemRv.circleNewMessage.visibility = View.VISIBLE
            } else {
                itemRv.circleNewMessage.visibility = View.INVISIBLE
            }

            if (user.online == true) {
                itemRv.circleOnline.setImageResource(R.color.Green_color)
            } else {
                itemRv.circleOnline.setImageResource(R.color.Gray)
            }

            itemRv.root.setOnClickListener {
                click.itemClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemUsersListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    interface Click {
        fun itemClick(user: Users)
    }

    override fun getItemCount(): Int = list.size
}
