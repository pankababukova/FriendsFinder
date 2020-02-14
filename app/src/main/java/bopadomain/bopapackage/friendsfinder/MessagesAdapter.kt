package bopadomain.bopapackage.friendsfinder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//creating a class "MessagesAdapter" for the controller
//Class "MessagesAdapter", extending from the RecyclerView (the controller) class with <ViewHolder> as parameter.
// All member functions are implemented in the class.
//array of type User (the model)
class MessagesAdapter (var context: Context, var list: ArrayList<User>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1)
        {
            var v = LayoutInflater.from(context).inflate(R.layout.sent_msg_layout, parent, false)
            return SentHolder(v)
        }
        else //if viewType is 2
        {
            var v = LayoutInflater.from(context).inflate(R.layout.received_msg_layout, parent, false)
            return ReceiverHolder(v)
        }
    }

    //the function retrieves how many rows will be returned in the viewer
    override fun getItemCount(): Int {
        return list.size
    }

    //a function to distinguish between the sent and receiver holder
    //var position retrieves the number of rows

    override fun getItemViewType(position: Int): Int {
        if (list[position].username=="me") //if i am the sender of the message
            return 1
        else
            return 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(list[position].username=="me") //if i am the sender, show as sent message
            (holder as SentHolder).show(list[position].msg)
        else
            (holder as ReceiverHolder).show(list[position].msg)
    }

    //creating two view holders for each msg type with a var and a fun to show the msg
    public class SentHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var textV = itemView.findViewById<TextView>(R.id.textview_msg) as TextView
        fun show(msg:String)
        {
            textV.text=msg
        }
    }

    public class ReceiverHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var textV = itemView.findViewById<TextView>(R.id.textview_msg) as TextView
        fun show(msg:String)
        {
            textV.text=msg
        }
    }
}