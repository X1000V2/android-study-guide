package luis.barroso.androidstudyguide.androidCore.jetpack.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_row.view.*
import luis.barroso.androidstudyguide.R
import java.util.zip.Inflater

class UserAdapter(var items: List<UserEntity>, private val onDelete: (user: UserEntity) -> Unit): RecyclerView.Adapter<UserAdapter.UserView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserView {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_row, parent, false)

        return UserView(view,onDelete)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UserView, position: Int) {
        holder.bind(items[position])
    }

    class UserView(itemView: View, val onDelete: (user: UserEntity) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val textViewName: TextView = itemView.findViewById(R.id.text_view_user_name)
        private val textViewLastName: TextView = itemView.findViewById(R.id.text_view_user_last_name)
        private val textViewAge: TextView = itemView.findViewById(R.id.text_view_user_age)
        private val imageViewDelete: ImageView = itemView.findViewById(R.id.item_view_remove_item)

        fun bind(user: UserEntity){
            textViewName.text = user.name
            textViewLastName.text = user.lastName
            textViewAge.text = "${user.age}"
            imageViewDelete.setOnClickListener { onDelete(user) }
        }
    }
}