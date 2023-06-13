package user_function;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import user_function.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author junli
 */
public class Profile extends JFrame {

    private JLabel format_txt(String txt) {
        JLabel msg = new JLabel(txt);
        msg.setFont(font);
        return msg;
    }

    // Declare the components
    private ArrayList<String> ids;
    private JComboBox<String> chCourse;
    JFileChooser filechooser;
    private JButton save_button;
    private JTextField textField_for_naming;
    private JButton Miao;
    Font font;
    JFrame profile;

    // Constructor
    /**
     *
     * @param width
     * @param height
     */
    public Profile(int width, int height, String Username) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        String[] user_info = new data().user_readout(Username);

        profile = new JFrame();
        profile.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // for user-log
        System.out.println("Enter profile");
        width = (int) (width * 0.6);
        height = (int) (height * 0.6);
        profile.setSize(width, height);
        profile.setTitle("Profile"); // set title here

        profile.setLayout(new GridBagLayout());
        // font-size
        font = new Font("Arial", Font.PLAIN, width / 30);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        // save button
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5;
        JTextField searchbar = new JTextField();
        searchbar.setFont(font);
        profile.add(searchbar, constraints);

        constraints.gridx = 5;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        JButton searchbutton = new JButton("Search");
        searchbutton.setFont(font);
        profile.add(searchbutton, constraints);

        //button of name 
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        JLabel naming_label = new JLabel("Name: ");
        naming_label.setFont(font);
        profile.add(naming_label, constraints);

        //the display of the name column also the place to be edit 
        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        textField_for_naming = new JTextField(10);
        textField_for_naming.setText(Username);
        textField_for_naming.setFont(font);
        textField_for_naming.setEditable(false);
        profile.add(textField_for_naming, constraints);

        // Email
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        JLabel lblemail = new JLabel("Email: ");
        lblemail.setFont(font);
        profile.add(lblemail, constraints);

        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        JTextField textField_for_email = new JTextField(20);
        textField_for_email.setText(user_info[1]);
        textField_for_email.setEditable(false);
        textField_for_email.setFont(font);
        profile.add(textField_for_email, constraints);

        //BIO
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        JLabel lblBio = new JLabel("Bio: ");
        lblBio.setFont(font);
        profile.add(lblBio, constraints);

        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        constraints.gridheight = 2;
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(width / 5, width / 5));

        JTextArea textField_for_bio = new JTextArea();
        textField_for_bio.setText(user_info[2].replace("|", "\n"));
        textField_for_bio.setLineWrap(true);
        textField_for_bio.setWrapStyleWord(true);

        textField_for_bio.setEditable(false);
        textField_for_bio.setFont(font);

        panel.add(new JScrollPane(textField_for_bio), BorderLayout.CENTER);
        profile.add(panel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        JButton edit_button = new JButton("Edit");
        profile.add(edit_button, constraints);
        profile.setVisible(true);
        edit_button.setFont(font);

        constraints.gridx = 3;
        constraints.gridy = 5;
        constraints.gridwidth = 3;
        save_button = new JButton("Save");
        profile.add(save_button, constraints);
        profile.setVisible(true);
        save_button.setFont(font);
        save_button.setEnabled(false);

        searchbutton.addActionListener((ActionEvent e) -> {
            // get username profile
            String[] userinfo = new data().user_readout(searchbar.getText());
            //settxt
            if (userinfo[0] == "None") {
                JOptionPane.showMessageDialog(profile, format_txt("user not exist"));
            } else {
                textField_for_naming.setText(userinfo[0]);
                textField_for_email.setText(userinfo[1]);
                textField_for_bio.setText(userinfo[2].replace("|", "\n"));

                // button only avaliable for admin
                System.out.println(userinfo[3] + " ok");
                if ("admin".equals(user_info[3])) {
                    edit_button.setEnabled(true);
                    save_button.setEnabled(false);
                } else if (searchbar.getText().equals(Username)) {
                    edit_button.setEnabled(true);
                    save_button.setEnabled(false);
                } else if (!"admin".equals(user_info[3]) && !searchbar.getText().equals(Username)) {
                    edit_button.setEnabled(false);
                    save_button.setEnabled(false);
                }
            }

        });

        edit_button.addActionListener((ActionEvent e) -> {
            // do something
            // disable the button when the new JFrame is opened
            edit_button.setEnabled(false);
            save_button.setEnabled(true);

            textField_for_naming.setEditable(true);
            textField_for_email.setEditable(true);
            textField_for_bio.setEditable(true);

        });

        save_button.addActionListener((ActionEvent e) -> {

            // if statement check sentence without : or ; and replace \n    
            if (textField_for_bio.getText().contains(";") || textField_for_bio.getText().contains(":") || textField_for_bio.getText().contains("|") || textField_for_bio.getText().contains("\\")) {
                JOptionPane.showMessageDialog(profile, format_txt("Bio can't include |, : and ;"));
            } else {

                // do something
                String result = new data().profile_save(Username, textField_for_naming.getText(), textField_for_email.getText(), textField_for_bio.getText().replace("\n", "|"));
                if (result == "email exist") {
                    JOptionPane.showMessageDialog(profile, format_txt("Email already exist"));
                } else if (result == "email format") {
                    JOptionPane.showMessageDialog(profile, format_txt("please confirm your email"));
                } else if (result == "username exist") {
                    JOptionPane.showMessageDialog(profile, format_txt("username already exist"));
                }

                System.out.println(result);
            }
            // disable the button when the new JFrame is opened
            save_button.setEnabled(false);

            textField_for_naming.setEditable(false);
            textField_for_email.setEditable(false);
            textField_for_bio.setEditable(false);
            edit_button.setEnabled(true);

        });
        profile.setVisible(true);
        profile.setLocationRelativeTo(null); // Center the frame on the screen
    }

    ;
    
    @Override
    public void addWindowListener(WindowListener listener) {
        this.profile.addWindowListener(listener);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(() -> {
            
        });*/
        new Profile(1000, 1200, "poig");
    }
}
