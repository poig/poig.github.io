package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import user_function.data;

public class RegisterPanel extends JPanel {

    Font font;

    private JLabel format_txt(String txt) {
        JLabel msg = new JLabel(txt);
        msg.setFont(font);
        return msg;
    }

    public RegisterPanel(JFrame frame, int width) {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        // font-size
        font = new Font("Arial", Font.PLAIN, width / 30);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);
        frame.setTitle("Register Form"); // set title here

        // add components for registration screen here
        // username
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(font);
        frame.add(usernameLabel, constraints);

        constraints.gridx = 1;
        JTextField usernameField = new JTextField(10);
        usernameField.setFont(font);
        frame.add(usernameField, constraints);

        // gmail
        constraints.gridx = 0;
        constraints.gridy = 1;

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(font);
        frame.add(emailLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        JTextField emailField = new JTextField(10);
        emailField.setFont(font);
        frame.add(emailField, constraints);

        // password
        constraints.gridx = 0;
        constraints.gridy = 2;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(font);
        frame.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        JPasswordField passwordField = new JPasswordField(12);
        passwordField.setFont(font);
        frame.add(passwordField, constraints);

        // password
        constraints.gridx = 0;
        constraints.gridy = 3;
        passwordLabel = new JLabel("Confirm Password:");
        passwordLabel.setFont(font);
        frame.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        JPasswordField ConfirmPasswordField = new JPasswordField(12);
        ConfirmPasswordField.setFont(font);
        frame.add(ConfirmPasswordField, constraints);

        // register button
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        JButton RegisterButton = new JButton("Register");
        RegisterButton.setFont(font);
        frame.add(RegisterButton, constraints);
        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // display login screen
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                char[] ConfirmPassword = ConfirmPasswordField.getPassword();
                String gmail = emailField.getText();
                //todo: logic for adding new user
                String[] result = new data().user_readout(username);
                List<String> gmail_list = new data().gmail_list();

                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, format_txt("Please Enter your username."));
                } else if (gmail.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, format_txt("Please Enter your Email."));
                } else if (!gmail.contains("@")) {
                    JOptionPane.showMessageDialog(frame, format_txt("Please Enter correct Email format."));
                } else if (password.length == 0) {
                    JOptionPane.showMessageDialog(frame, format_txt("Please Enter Your password."));
                } else if (ConfirmPassword.length == 0) {
                    JOptionPane.showMessageDialog(frame, format_txt("Please confirm password."));
                } else if (!Arrays.equals(password, ConfirmPassword)) {
                    JOptionPane.showMessageDialog(frame, format_txt("Check your confirm password"));
                } else {
                    if (!result[0].equals("None")) {
                        JOptionPane.showMessageDialog(frame, format_txt("Username already exist."));
                    } else if (gmail_list.contains(gmail)) {
                        JOptionPane.showMessageDialog(frame, format_txt("Gmail already exist."));
                    } else {
                        new data().user_register(username, new String(password), gmail, "user");
                        JOptionPane.showMessageDialog(frame, format_txt("Congrat!!! You Registered, return to login page."));
                        // display login screen
                        frame.getContentPane().removeAll();
                        new LoginPanel(frame, width);
                        frame.revalidate();
                        frame.repaint();
                    }
                }
                System.out.println();

            }
        });

        // create return login button
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        JButton returnLoginButton = new JButton("Return to Login");
        returnLoginButton.setFont(font);
        frame.add(returnLoginButton, constraints);
        // add action listener to return login button
        returnLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // display login screen
                frame.getContentPane().removeAll();
                new LoginPanel(frame, width);
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}
