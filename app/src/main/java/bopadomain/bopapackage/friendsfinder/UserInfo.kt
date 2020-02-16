package bopadomain.bopapackage.friendsfinder

//creating a global var for the user
//companion object = static object
//??? How to relate this User class to the Realtime database
class UserInfo {

    companion object {
        var me:String = ""
        var friend:String = ""
    }
}