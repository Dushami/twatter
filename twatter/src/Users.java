/**
 * THIS IS JUST A TEMP USERS CLASS, HAMISH IS MAKING THE ACTUAL ONE
 * THIS HAS BEEN USED TO TEST LOGIN ASSUMING WE USE THESE FIELDS
 */

public class Users {
    public int userID;
    public String username;
    public String password;
    public String name;
    public String currentWorkplace;
    public String hometown;

    public Users(int userID, String username, String password, String name, String currentWorkplace, String hometown) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.currentWorkplace = currentWorkplace;
        this.hometown = hometown;
    }
}