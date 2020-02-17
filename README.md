# FriendsFinder

MainActivity - log in or register an account with email address and password.
Every signed in user is added to the Firebase Realtime database with a username and an automatically generated message. 
The username is authomatically created from the email, default msg is "Hello!". 
This default msg is updated in the databse when the user send a new msg.

UserProfileActivity.kt contains a list view of all logged in users (we can test this with two emulators and two logged users).
For the UserProfileActivity, the users are distinguished in a class UserInfo.kt with variables "me" and "friend".
"Me" is not shown in the list view (the user is not supposed to see themselves), only the other logged in users are shown as friends.
This activity contains a button to lead the user to the map and a button to sign out from the account. 

A local SQLite database is created in the class ChatDatabase.kt. The local database enables offline usage of the chat.
The table "chat.db" has two columns, a key and a value. The key is the username, value is the message. 

The chat function is implemented in the ChatActivity via Recycler view. The Recycler view needs a viewer, a model and a controller.
The viewer is created in two layouts - "received_msg_layout.xml" and "sent_msg_layout.xml".
The model is the Class User.kt with vars username and msg.
The controller is coded in the class MessageAdapter.kt that contains the array of the class User. 
This controller distinguishes between the sent and received messages and the users (if the user is "me" and all the rest).

The ChatActivity.kt contains the array list of the User class (the model for the Recycler view), the message adapter, 
an object to refer to the Realtime DB and an object of the local SQLite DB.
The chat button sends data to the local SQLite and the realtime database.

The maps activity enables sharing the user's location. 
The coupon has expired on 10th FEb and this function cannot be tested at the moment.

Run the app:
The app should be run in two emulators simultaneously, two different users should be logged in each of the emulators.
At the moment the list of users appears in only one of the emulators, thus chat can be enabled only from one user.
Default messages send in the chat appear as "null" instead of "Hello!". 
Screenshots to visualise the testing are available. 

The second user is not notified when receiving a message, this function should be added in future.
