package bopadomain.bopapackage.friendsfinder

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//class to create a local database with parameters: context, name of the file, no factory and 1 version of the DB
//if user has not internet connection, the data will be stored locally

class ChatDatabase(context: Context) : SQLiteOpenHelper(context, "chat.db", null, 1) {
    override fun onCreate(p0: SQLiteDatabase) {  //p0 var of type SQLiteDatabase executes the SQL statement
        p0.execSQL("create table chat(user text, msg text)") //table of the DB "chat"
        p0.execSQL("insert into chat values ('me', 'Hello')") /* insert rows of the table */
        p0.execSQL("insert into chat values ('Bojan', 'Hi')")
        
//the chat activity shows those 2 msgs and then updates only the sent msgs. default received messages "null" are received after every sent msg
// as only one emulator activates the chat, so only one user can send msgs.
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}
