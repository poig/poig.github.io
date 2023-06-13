package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import user_function.data;
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author junli
 */
public class main {

    public static String inp1, inp2, inp3;
    public static int count = 1;

    public static void main(String[] args) {

        // so all window should have the same UI settings
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.5);
        int height = (int) (screenSize.getHeight() * 0.5);
        System.out.println("width, height: " + width + ", " + height);
        frame.setSize(width, height);

        //URL url = main.class.getProtectionDomain().getCodeSource().getLocation();
        //String jarPath = url.getPath();
        URL url = main.class.getProtectionDomain().getCodeSource().getLocation();
        String jarPath = url.getPath();
        File jarFile = new File(jarPath);
        String parentDir = jarFile.getParent();
        //System.out.println(parentDir+"\\userdata\\comments.txt");

        String userdata_dict = "/userdata/user_dictionary.txt";
        // origin: jarPath + userdata_dict

        File userdata = new File(parentDir + userdata_dict);
        if (!userdata.exists()) {
            new data().user_register("admin", "admin", "admin@gmail.com", "admin");
            new data().user_register("user1", "user1", "user1@gmail.com", "user");
            new data().user_register("miao", "miao", "miao@gmail.com", "user");
        }

        File userdatas = new File(parentDir + "/userdata/comments.txt");
        if (!userdatas.exists()) {
            String sample_comment = "testn :  Hello World\n"
                    + "poig :  No, there will be no world in the future, it is going to end.\n"
                    + "test :  what is your name\n"
                    + "admin :  yah, becaue chaos is going to end because of entropy theory.\n"
                    + "poig :  yah, the second law of thermodynamics, when we breath and be alive is actually pushing the process, we should always be grateful to the beauty of nature.\n"
                    + "test :  ok";
            new data().user_comment(sample_comment);
        }

        new LoginPanel(frame, width);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
    }
}
