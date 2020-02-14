package bopadomain.bopapackage.friendsfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register2.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

        create_acc_button.setOnClickListener{
            if (register_password.text.toString() == confirm_password.text.toString())
            {
                var auth = FirebaseAuth.getInstance() //creating an object from the authentication
                auth.createUserWithEmailAndPassword(register_email.text.toString(),
                    register_password.text.toString()).addOnCompleteListener{task ->
                    if (task.isSuccessful)
                    {
                        //authomatically creating the username from the 1st character til the @
                        UserInfo.me = register_email.text.toString().substring(0,
                            register_email.text.toString().indexOf(("@")))
                        //adding the username to the realtime database and a default message hello
                        var dbUser = FirebaseDatabase.getInstance().reference
                        dbUser.child("users").child(UserInfo.me).setValue("Hello!")

                        var intent = Intent(this, UserProfileActivity:: class.java)
                        startActivity(intent)
                    }
                    else
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show() //displaying an error message if login unsuccessful
                }

            }
            else
                Toast.makeText(this, "Password not identical", Toast.LENGTH_LONG).show()
        }
    }
}
