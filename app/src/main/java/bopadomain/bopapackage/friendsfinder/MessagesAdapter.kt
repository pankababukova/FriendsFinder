package bopadomain.bopapackage.friendsfinder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
// Recycler view contains:
// 1) View - the 2 layouts for the sent and received messages;
// 2) Model - the class User with vars username and msg
// 3) Controller - the class MessageAdapter, extending from the RecyclerView (the controller) class with <ViewHolder> as parameter.

//the model is saved in a var list)
class MessagesAdapter (var context: Context, var list: ArrayList<User>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

// 4. a function to distinguish between the sender and receiver view holders
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

// 2. the function retrieves how many rows will be returned in the viewer, namely the length of the list
    override fun getItemCount(): Int {
        return list.size
    }

// 3. a function to distinguish between the sent and receiver holder
//var position retrieves the number of rows

    override fun getItemViewType(position: Int): Int {
        if (list[position].username=="me") //if i am the sender of the message
            return 1
        else
            return 2
    }
//4. The parameter holder distinguishes between the holders
    //if I anm the sender the viewholder shall display the SentHolder (sent msg)
    //the fun is implemented in the Chat activity
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(list[position].username=="me") //if i am the sender, show as sent message
            (holder as SentHolder).show(list[position].msg)
        else
            (holder as ReceiverHolder).show(list[position].msg)
    }

//1. creating two view holders for the 2 message layouts with a var and a fun to show the msg
    public class SentHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var textV = itemView.findViewById<TextView>(R.id.textview_msg) as TextView //the var looks for an item of the type TextView with id textview_msg
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