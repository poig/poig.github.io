package user_function;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author poig
 */
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class user_profile {

    String password;
    String gmail;
    String bio;

    user_profile(String password, String gmail, String bio) {
        this.password = password;
        this.gmail = gmail;
        this.bio = bio;
    }
}

public class data {

    public Map user_info() {
        // for user login
        Map<String, user_profile> empty_user = new HashMap<>();
        // read folder
        try (RandomAccessFile reader = new RandomAccessFile(new File("userdata/dictionary.txt"), "r")) {
            //}
            //try (BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(": ");
                String[] user_parts = parts[1].split(";");
                empty_user.put(parts[0], new user_profile(user_parts[0], user_parts[1], user_parts[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return empty_user;

    }

    public List gmail_list() {
        Map empty_user = user_info();
        List gmails = new ArrayList();
        for (Iterator it = empty_user.values().iterator(); it.hasNext();) {
            user_profile profile = (user_profile) it.next();
            gmails.add(profile.gmail);
        }
        return gmails;
    }

    public String[] user_readout(String username) {

        Map empty_user = user_info();
        try {
            user_profile userinfo = (user_profile) empty_user.get(username);
            String[] returninfo = {userinfo.password, userinfo.gmail, userinfo.bio};
            return returninfo;
        } catch (RuntimeException e) {
            System.out.println("user not exist");
            String[] none = {"None"};
            return none;
        }
    }

    public String user_login(String username, String passwords) {

        String UserPassword = user_readout(username)[0];

        //empty_user.get(username);
        try {
            if (UserPassword.equals(passwords)) {
                return "pass";
            } else {
                return "error";
            }
        } catch (RuntimeException e) {
            return "none";
        }

    }

    ;

    public static void user_register(String username, String password, String gmail) {
        // for user register

        // create data structure
        Map<String, user_profile> sample_user = new HashMap<>();
        sample_user.put(username, new user_profile(password, gmail, ""));
        //sample_user.put("poig", new user_profile("poig", "poig@gmail.com", "so fucked"));

        File userdata = new File("userdata/dictionary.txt");
        userdata.getParentFile().mkdirs();
        try {
            if (!userdata.exists()) {
                // create and write folder
                RandomAccessFile writer = new RandomAccessFile(userdata, "rw");

                for (Map.Entry<String, user_profile> entry : sample_user.entrySet()) {
                    writer.writeBytes(entry.getKey() + ": " + entry.getValue().password + "; " + entry.getValue().gmail + "; " + entry.getValue().bio + "\n");
                }
                System.out.println("create and write user file");

            } else {
                // if already exist check if not exist then add them
                try (FileWriter writer = new FileWriter("userdata/dictionary.txt", true)) {

                    for (Map.Entry<String, user_profile> entry : sample_user.entrySet()) {
                        writer.write(entry.getKey() + ": " + entry.getValue().password + "; " + entry.getValue().gmail + "; " + entry.getValue().bio + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("append user file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void user_comment(String comments) {

        File usercomment = new File("userdata/comments.txt");
        try {
            RandomAccessFile writer = new RandomAccessFile(usercomment, "rw");

            writer.writeBytes(comments);
            System.out.println("create and write commment file" + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read_user_comment() {
        StringBuilder sb = new StringBuilder();
        try (RandomAccessFile reader = new RandomAccessFile(new File("userdata/comments.txt"), "r")) {
            //}
            //try (BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"))) {
            String line = reader.readLine();
            
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = reader.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "none";
    }
    
    public String edit_user(){
        return "";
    }

    public void quiz_read() {
        // for readout quiz
    }

    public void quiz_edit() {
        // for admit changes
    }

    public static void main(String[] args) {
        //new user_login("what", "123");
        //String result = new data().user_login("admin", "admin");
        //System.out.println(result);

        new data().user_comment("test");
        String result = new data().read_user_comment();
        System.out.print(result);

    }

}
