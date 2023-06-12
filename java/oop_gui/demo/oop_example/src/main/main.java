package main;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import user_function.data;

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

        File userdata = new File("userdata/dictionary.txt");
        if (!userdata.exists()) {
            data.user_register("admin", "admin", "admin@gmail.com", "admin");
        }
        
        
        File userdatas = new File("userdata/comments.txt");
        if (!userdatas.exists()) {
            new data().user_comment("");
        }
        
        new LoginPanel(frame, width);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
    }
}
