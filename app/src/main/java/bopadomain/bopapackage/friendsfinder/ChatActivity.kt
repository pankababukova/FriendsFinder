package bopadomain.bopapackage.friendsfinder

//import android.raywenderlich.friendsfinder.R

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_chat.*


class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        //var txtChat : TextView = findViewById(R.id.MyChatsHeader)
        //txtChat.setText("Welcome to your chats!")

        var db_obj = FirebaseDatabase.getInstance().reference // 1) defining an object to refer to the realtime database

        var list=ArrayList<User> ()
        //list.add(User("me", "Hello!"))
        //list.add(User("bojan", "Hi there!"))

        //defining an object of the DB to fill in the list
        var obj = ChatDatabase(this)
        var db = obj.writableDatabase //the var is writing data in the DB
        var cur = db.rawQuery("select * from chat", null) //retrieve all data from the chat and store in the cur var (Cursor)
        cur.moveToFirst() //makes the pointer of the cursor in the first row, to open the chat database
        while(cur.isAfterLast==false)
        {
            //get the username from the cursor
            list.add(User(cur.getString(0), cur.getString(1))) //get the first two columns (data from the 1st and 2nd row / username and message
            cur.moveToNext() //instead of plus plus in the loop
        }

        //creating an object for the messageAdapter and pass it as this
        var adp= MessagesAdapter(this, list) //the adapter object provides access to the date items
        chat_recyclerView.adapter=adp    // creating a relationship with the RecyclerView
        chat_recyclerView.layoutManager=LinearLayoutManager(this)
        chat_recyclerView.scrollToPosition(list.size-1) // always display the chat from the last message, not the first one

        send_msg_btn.setOnClickListener{
            //two unknown variables to be added to the DB
            db.execSQL("insert into chat values (?, ?)",arrayOf("me", edit_text_chat.text.toString())) //passes the text entered in the editText viewer
            list.add(User("me", edit_text_chat.text.toString())) //the values will be passed also to the list

            db_obj.child("users").child(UserInfo.friend).setValue(edit_text_chat.text.toString()) // 2) sending a msg to the selected user in the list

            adp.notifyDataSetChanged() //to restart the Adapter after the user clicks the Send button
            edit_text_chat.setText("") //to remove the text from the editor after sending the message
            chat_recyclerView.scrollToPosition(list.size-1) //always display the chat from the last message, not the first one
        }

        db_obj.child("users").child(UserInfo.me).addValueEventListener( //defining an object of the VAlueEventListener and implement the methods
            object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    //everz msg from the user friend will be added to the list and to the DB, the adapter will be notified
                    db.execSQL("insert into chat values (?, ?)",
                        arrayOf(UserInfo.friend, p0.getValue().toString())) //getting the value from the p0

                    list.add(User(UserInfo.friend, p0.getValue().toString())) //the values will be passed also to the list

                    adp.notifyDataSetChanged() //to restart the Adapter after the user clicks the Send button

                    chat_recyclerView.scrollToPosition(list.size-1) //always display the chat from the last message, not the first one
                }
            }
        )
    }
}
