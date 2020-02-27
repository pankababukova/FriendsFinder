# FriendsFinder
The app consists of 5 activities: Main Acticity (Login), Register Activity, User Profile Activity, Maps Activity and Chat Activity

1. MainActivity - log in into user's account with email address and password.
  Every signed in user is added to the Firebase Realtime database with a username and an automatically generated message. 
  The username is authomatically created from the email (characters before the '@' symbol), default msg is "Hello!". 
  This default msg gets updated in the database when the user sends a new msg.
  After successful login the user is lead to the User Profile Activity.
  If the user has no account, the register button leads the user to the register acitivity to create an account.
  
2. Register Activity - the user can craete an account with email address, password and password confirmation. 
  Every registered account is saved in the Users database.
  After successful registration, the user is lead to the User Profile Activity. 

3. The User Profile Activity contains a list view of all logged in users (we can test this with two emulators and two logged users).
  The user is distinguished from the other users in a class UserInfo.kt with variables "me" and "friend".
  The user themselves ("me") is not shown in the list view (the user is not supposed to see themselves). 
  The other logged in users are shown as a list in a list view.
  This activity contains a button to lead the user to share location and a button to sign out from the account. 
  The sign out button leads to the Main Activity (Login).
  
4. The Maps activity enables sharing the user's location. 
  The user's location shall be displayed with a marker. 
  The user can see a marker in different color representing the shared location of a friend - to be enabled. 
  The APIs-coupon has expired on 10th Feb and the shared-location function cannot be tested at the moment.
  
5. Chat Acitivity 
  The chat function is implemented in the Chat Activity via Recycler view. The Recycler view needs a viewer, a model and a controller.
  The viewer of the messages is created in two separate layouts - "received_msg_layout.xml" and "sent_msg_layout.xml".
  The model is the class User.kt with variables for the user and the sent message 'username' and 'msg'.
  When a user sends a message, it gets updated in the Realtime database. 
  The user shall be notified when receiving a message - to be enabled.
  The controller is coded in the class 'MessageAdapter.kt' that contains the array of the class User. 
  This controller distinguishes between the sent and received messages and the users (if the user is "me" or else).
  The Chat Activity contains the array list of the User class (the model for the Recycler view), the message adapter, 
  an object to refer to the Realtime DB and an object to refer to the local SQLite DB.
  The chat button Send sends data to the local SQLite and the realtime database.

  A local SQLite database is created in the class 'ChatDatabase.kt'. The local database enables offline usage of the chat.
  The table "chat.db" has two columns, a key and a value. The key is the username, value is the message. 

Run the app:
  The app should be run in two emulators simultaneously, two different users should be logged in each of the emulators.
  At the moment the list of users appears in only one of the emulators, thus chat can be enabled only from one user.
  Default messages send in the chat appear as "null" instead of "Hello!". This error is new, the model used to work.
  Screenshots to visualise the testing are available. 

Maybe the error with the default message comes from the fact the both the SQLite database (ChatDatabase.kt) and the class User.kt 
contain the same variable var 'msg'. Still testing. 

Apart from that, the activities work, the app displays all toasts which are enabled, the database gets updated. 
Screenshots from the tesitng are available in the Screenshots forlder. 

