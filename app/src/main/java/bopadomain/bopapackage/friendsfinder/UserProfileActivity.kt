package bopadomain.bopapackage.friendsfinder

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        signout_button.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent (this, MainActivity::class.java))
        }

        go_to_map_button.setOnClickListener{
            var obj = Intent(applicationContext, MapsActivity::class.java)
            startActivity(obj)
        }
    //in order to show the items in the list view, we need to create an adapter
    //creating var for the users and a list for the users
        var dbUser = FirebaseDatabase.getInstance().reference //getting instance from the database
        var list = ArrayList<String>() //the list of the adapter

        //object overwriting all the methods from valueEventListener
        dbUser.child("users").addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                //the variable p0 retrieves all data from the "users" realtime database
                // retrieve the key from all database children (key = username) and add them to the list in the listview
                for (i in p0.children)
                {
                    if (i.key != UserInfo.me)  //doesnt add the user to the list of friends
                        list.add(i.key.toString())
                }

        //displaying the retrieved data
        //var to display value in array adapter with parameter
        //adp will be filled in the users list view (UserProfile Activity)
        // android.R.layout.simple_list_item_1 = uses system resources
                var adp = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, list)
                users_list.adapter = adp

        //adding an event for itemClickListener for the users list view
        //when a user logged into the system clicks on any user in the list (list[u])
        //then move the user and the selected user to the ChatActivity
                users_list.setOnItemClickListener{ adapterView, view, u, l  ->
                    //stores the username of the friend that has been clicked on
                    //this list is the "simple_list_item_1"
                    UserInfo.friend = list[u]
                    var obj = Intent(applicationContext, ChatActivity::class.java)// moving the user from the current activity to the chat activity
                    startActivity(obj)
                }
            }
        })
    }
}
