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


//5. Relating the local SQLite Databse to the REaltime Database
//5.1. defining an object to refer to the realtime database
        var obj_rt_db = FirebaseDatabase.getInstance().reference

//1. A variable for the model User
        var list=ArrayList<User>() //data type of the model User
        //first we create a test database then replace it with the real Chat Database:
        //list.add(User("me", "Hello!"))
        //list.add(User("bojan", "Hi there!"))

//3. defining an object of the local DB (SQLite) to fill in the list
        var obj_sqlite = ChatDatabase(this)
        var obj_write_sqlite = obj_sqlite.writableDatabase //the var is writing data in the DB
        var cur = obj_write_sqlite.rawQuery("select * from chat", null) //retrieve all data from the chat and store in the cur var (Cursor)
        cur.moveToFirst() //makes the pointer of the cursor in the first row, to open the chat database
        while(cur.isAfterLast==false)
        {
            //get the username from the cursor
            list.add(User(cur.getString(0), cur.getString(1))) //get the first two columns (data from the 1st and 2nd row / username and message
            cur.moveToNext() //instead of plus plus in the loop
        }

//2. Variable for the MessageAdapter
        //creating an object for the messageAdapter and pass it as this
        var msg_adp= MessagesAdapter(this, list) //the adapter object provides access to the date items
        chat_recyclerView.adapter=msg_adp    // creating a relationship with the RecyclerView
        chat_recyclerView.layoutManager=LinearLayoutManager(this)
        chat_recyclerView.scrollToPosition(list.size-1) // always display the chat from the last message, not the first one

//4. enabling the chat button
        send_msg_btn.setOnClickListener{
            //two unknown variables from the SQLite to be added to the DB
            //arrayOf passes the value to the unknown variable, passes the text entered in the editText viewer
            obj_write_sqlite.execSQL("insert into chat values (?, ?)",arrayOf("me", edit_text_chat.text.toString()))
            list.add(User("me", edit_text_chat.text.toString())) //the values of the Model USer will be passed to the list too

//5.2. sending a msg to the user, that has been selected from the UserProfile Activity, from the users list view
//the list is related to the SQLite databse "users"
            obj_rt_db.child("users").child(UserInfo.friend).setValue(edit_text_chat.text.toString())
            msg_adp.notifyDataSetChanged() //to restart the Adapter after the user clicks the Send button
            edit_text_chat.setText("") //to remove the text from the editor after clicking the send button
            chat_recyclerView.scrollToPosition(list.size-1) //always display the chat from the last message, not the first one
        }
//5.3. receiving a message
        obj_rt_db.child("users").child(UserInfo.me).addValueEventListener( //defining an object of the VAlueEventListener and implement the methods
            object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
//everz msg from the user friend will be added to the list and to the DB, the adapter will be notified
    //the sent msg is stored in the Realtime Database "users"
    // adding the data from the database into the chat activity
    // copying the call to receive data (msg) from the database by using UserInfo.friend
    // msg gets the value of p0 = msg comes from the upper user (UserInfo.me)
    //every msg coming from the user will be added to sqlite table + arrayList User + Adapter will be notified
                    obj_write_sqlite.execSQL("insert into chat values (?, ?)",
                        arrayOf(UserInfo.friend, p0.getValue().toString())) //getting the value from the p0
                    list.add(User(UserInfo.friend, p0.getValue().toString())) //the values will be passed also to the User list
                    msg_adp.notifyDataSetChanged() //to restart the Adapter after the user clicks the Send button
                    chat_recyclerView.scrollToPosition(list.size-1) //always display the chat from the last message, not the first one
                }
            }
        )
    }
}
