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
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class user_profile {

    String password;
    String gmail;
    String admin;
    String bio;

    user_profile(String password, String gmail, String admin, String bio) {
        this.password = password;
        this.gmail = gmail;
        this.admin = admin;
        this.bio = bio;
    }
}

public class data {

    URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
    String jarPath = url.getPath();
    File jarFile = new File(jarPath);
    String parentDir = jarFile.getParent();

    //String user_dict = "\\userdata\\user_dictionary.txt";
    //String userdata_dict = "\\" + parentDir + user_dict;
    String userdata_dict = "/userdata/user_dictionary.txt";

    public Map user_info() {
        // for user login
        Map<String, user_profile> empty_user = new HashMap<>();
        // read folder

        //try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(parentDir.replace("\\", "\\\\")+"\\\\user_dictionary.txt")))) {
        try (BufferedReader reader = new BufferedReader(new FileReader(parentDir + userdata_dict))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(": ");
                String[] user_parts = parts[1].split("; ");
                // possible broblem in here is there is double \n exist in txt
                empty_user.put(parts[0], new user_profile(user_parts[0], user_parts[1], user_parts[2], user_parts[3]));
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
            String[] returninfo = {userinfo.password, userinfo.gmail, userinfo.bio, userinfo.admin};
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

    public void user_register(String username, String password, String gmail, String admin) {
        // for user register

        // create data structure
        Map<String, user_profile> sample_user = new HashMap<>();
        sample_user.put(username, new user_profile(password, gmail, admin, " "));
        //String userdata_dict = "userdata/user_dictionary.txt";

        //File userdata = new File(jarPath+userdata_dict);
        File userdata = new File(parentDir + userdata_dict);
        userdata.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(userdata.getPath(), true);) {
            if (!userdata.exists()) {
                // create and write folder
                System.out.println(userdata.getPath());

                //RandomAccessFile writer = new RandomAccessFile(userdata, "rw");
                //FileWriter writer = new FileWriter(userdata.getPath());
                for (Map.Entry<String, user_profile> entry : sample_user.entrySet()) {
                    writer.write(entry.getKey() + ": " + entry.getValue().password + "; " + entry.getValue().gmail + "; " + entry.getValue().admin + "; " + entry.getValue().bio + "\n");
                    System.out.println(entry.getKey());
                }
                System.out.println("create and write user file");

            } else {
                // if already exist check if not exist then add them
                //try (FileWriter writer = new FileWriter( userdata.getPath(), true)) {

                for (Map.Entry<String, user_profile> entry : sample_user.entrySet()) {
                    writer.write(entry.getKey() + ": " + entry.getValue().password + "; " + entry.getValue().gmail + "; " + entry.getValue().admin + "; " + entry.getValue().bio + "\n");
                }
                //} catch (IOException e) {
                //    e.printStackTrace();
                //}
                System.out.println("append user file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //String UserCommentFile = parentDir+"\\userdata\\comments.txt";
    String UserCommentFile = "/userdata/comments.txt";

    public void user_comment(String comments) {

        File usercomment = new File(parentDir + UserCommentFile);
        try (FileWriter writer = new FileWriter(usercomment.getPath(), true);) {
            //RandomAccessFile writer = new RandomAccessFile(usercomment, "rw");

            writer.write(comments);
            System.out.println("create and write commment file" + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read_user_comment() {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(parentDir + UserCommentFile));) {
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

    public void quiz_read() {
        // for readout quiz
    }

    public void quiz_edit() {
        // for admit changes
    }

    public String profile_save(String oldname, String newname, String gmail, String bio) {

        // get profile line number
        Map empty_user = user_info();

        // remove oldname
        user_profile user_value = (user_profile) empty_user.remove(oldname);

        // check if user exist
        user_profile userinfo = (user_profile) empty_user.get(newname);
        if (userinfo == null) {
            System.out.println("username not exist");

            // format email list
            List<String> gmail_list = new ArrayList();
            for (Iterator it = empty_user.values().iterator(); it.hasNext();) {
                user_profile profile = (user_profile) it.next();
                gmail_list.add(profile.gmail);
            }
            if (gmail.contains("@")) {
                if (!gmail_list.contains(gmail)) {
                    try (FileWriter writer = new FileWriter(new File(parentDir + userdata_dict).getPath(), true);) {
                        empty_user.put(newname, new user_profile(user_value.password, gmail, user_value.admin, bio));
                        // save
                        //File userdata = new File(parentDir + userdata_dict);
                        //RandomAccessFile writer = new RandomAccessFile(userdata, "rw");
                        //FileWriter writer = new FileWriter(new File(parentDir + userdata_dict).getPath(), true);

                        for (Iterator it = empty_user.entrySet().iterator(); it.hasNext();) {
                            Map.Entry<String, user_profile> entry = (Map.Entry<String, user_profile>) it.next();
                            writer.write(entry.getKey() + ": " + entry.getValue().password + "; " + entry.getValue().gmail + "; " + entry.getValue().admin + "; " + entry.getValue().bio + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    return "email exist";
                }

                return "saved";
            } else {
                return "email format";
            }
        } else {
            return "username exist";
        }

    }

}
