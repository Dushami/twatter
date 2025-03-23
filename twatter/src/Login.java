/**
 * Twatter group project, Log-in page
 *
 * @author Archie Hamilton
 * @version 1.0
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Login {

    //use an ArrayList to allow valaidaation of username & password
    static List<Users> users = new ArrayList<>();

    /**
     * loadUsers method to 
     * @param fileName whatever you file is called, in our case userData.txt
     */
    static void loadUsers(String fileName) {
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split(",");

                int id = Integer.parseInt(userData[0].trim());
                String username = userData[1].trim();
                String password = userData[2].trim();
                String name = userData[3].trim();
                String workplace = userData[4].trim();
                String hometown = userData[5].trim();

                users.add(new Users(id, username, password, name, workplace, hometown));
            }

            System.out.println("Users loaded!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } catch (IOException e) {
            System.err.println("Failed to load users!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    /**
     * handleLogIn will take the user input & either log them in or tell them there is no account with that detail
     * @param data scanner for whatever the user enters
     */
    static void handleLogIn(Scanner data){
        boolean loggedIn = false;
        while (!loggedIn) {
            //have the user input usernamme and password
            System.out.println("Please enter your username: ");
            String usernameInput = data.nextLine();
            System.out.println("Please enter your password: ");
            String passwordInput = data.nextLine();

            boolean accountFound = false;
            for (Users user : users) {
                if (user.username.equalsIgnoreCase(usernameInput) && user.password.equals(passwordInput)) {
                    System.out.println("successful login!!!!!!!!!!!!!!!!!!");
                    accountFound = true;
                    loggedIn = true;
                    break;
                }
            }
            if (!accountFound) {
                System.err.println("Invalid username or password, please try again");
            }
        }
    }

    static void createAccount(Scanner data) {
        System.out.println("Create a new Twatter account:");

        String username = "";
        boolean validUsername = false;

        while (!validUsername) {
            System.out.println("Enter new username: ");
            username = data.nextLine();
            String lowercaseUsername = username.toLowerCase();

            //see if the username is taken
            boolean taken = false;
            for (Users user : users) {
                if (user.username.toLowerCase().equals(lowercaseUsername)) {
                    System.err.println("Username already taken. Please try again.");
                    taken = true;
                    break;
                }
            }

            if (!taken) {
                validUsername = true;
            }
        }

        System.out.print("Enter your password: ");
        String password = data.nextLine();

        System.out.print("Enter your full name: ");
        String name = data.nextLine();

        System.out.print("Enter your current workplace: ");
        String workplace = data.nextLine();

        System.out.print("Enter your hometown: ");
        String hometown = data.nextLine();

        //find max ID and increment for new unique user
        int maxID = 0;
        for (Users user : users) {
            if (user.userID > maxID) {
                maxID = user.userID;
            }
        }
        int newUserID = maxID + 1;

        Users newUser = new Users(newUserID, username, password, name, workplace, hometown);
        users.add(newUser);

        //write to userData file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userData.txt", true))) {
            writer.newLine();
            writer.write(newUserID + "," + username + "," + password + "," + name + "," + workplace + "," + hometown);
            System.out.println("Account created successfully! You can now log in.");
        } catch (IOException e) {
            System.err.println("Failed to make new account");
        }
    }

    public static void main(String[] args) {

        /**
         * Load the 'userData' file which stores all user data
         * this may be the least secure program ever written
         */
        loadUsers("userData.txt");

        System.out.println("Twatter");
        System.out.println("1. Login\n" +
                "2. Create an account");
        System.out.print("Enter your choice: ");

        Scanner pick = new Scanner(System.in);
        int choice = pick.nextInt();
        pick.nextLine();

        Scanner data = new Scanner(System.in);
        if (choice == 1) {
            handleLogIn(data);
        } else if (choice == 2) {
            createAccount(data);
        } else {
            System.out.println("Invalid option.");
        }
    }
}