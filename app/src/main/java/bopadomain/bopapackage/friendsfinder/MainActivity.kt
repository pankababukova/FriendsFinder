package bopadomain.bopapackage.friendsfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

//import kotlinx.android.synthetic.main.activity_register.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signin_button.setOnClickListener {
            var auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(signin_email.text.toString(),
                signin_password.text.toString()).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    //
                    UserInfo.me = signin_email.text.toString().substring(0,
                        signin_email.text.toString().indexOf(("@")))

                    var intent = Intent(this, UserProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                    Toast.makeText(
                        this,
                        "Sign in Failed. Please try again.",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }

        register_button.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

//var username: String =    //extract the username from the login data
        //var db_root = FirebaseDatabase.getInstance().reference
        //db_root.child("users".child())

    }

        /*
         //inspired by: https://github.com/firebase/FirebaseUI-Android/blob/master/auth/README.md#authui-sign-in

        var providers=ArrayList<AuthUI.IdpConfig>()
        providers.add(AuthUI.IdpConfig.EmailBuilder().build())
        //providers.add(AuthUI.IdpConfig.PhoneBuilder().build())
        providers.add(AuthUI.IdpConfig.GoogleBuilder().build())
        providers.add(AuthUI.IdpConfig.FacebookBuilder().build())

        startActivityForResult(   //// Get an instance of AuthUI based on the default app
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(providers).build(), 123)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==123)
        {
            if(resultCode== Activity.RESULT_OK) //if login successfull, direct user to maps activity
            {
                var i=Intent(this, MapsActivity::class.java)
                startActivity(i)
            } else
                Toast.makeText(this, "Sign in Failed. Please try again.", Toast.LENGTH_LONG).show()
        }
    }
*/

}
