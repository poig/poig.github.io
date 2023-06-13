package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author junli
 */
import user_function.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class menu extends JPanel {

    public menu(String username) {
        // create new JFrame for new page here, but we want to direct to another class/ function

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame menu = new JFrame();
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.5);
        int height = (int) (screenSize.getHeight() * 0.5);
        // for user-log
        System.out.println("Enter menu");
        menu.setSize(width, height);
        menu.setTitle("menu"); // set title here

        menu.setLayout(new GridBagLayout());
        // font-size
        Font font = new Font("Arial", Font.PLAIN, width / 30);

        String access = new data().user_readout(username)[3];

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        JButton ArticleButton = new JButton("Article");
        ArticleButton.setFont(font);
        menu.add(ArticleButton, constraints);
        ArticleButton.addActionListener((ActionEvent e) -> {
            try {
                //do something
                ArticleButton.setEnabled(false);
                Article article = new Article(width, height, access);
                //menu.setVisible(false);

                // Add a WindowListener to enable the button when the new JFrame is closed
                article.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        ArticleButton.setEnabled(true);
                        //menu.setVisible(true);
                    }
                });
            } catch (IOException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridwidth = 8;
        JButton loginButton = new JButton("Quiz");
        loginButton.setFont(font);
        menu.add(loginButton, constraints);
        loginButton.addActionListener((ActionEvent e) -> {
            // do something
            // disable the button when the new JFrame is opened
            loginButton.setEnabled(false);
            quiz NewQuiz = new quiz(width, height);

            // Add a WindowListener to enable the button when the new JFrame is closed
            NewQuiz.addQuizWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loginButton.setEnabled(true);
                }
            });
        });

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        JButton calculatorButton = new JButton("Carbon Consumption Calculator");
        calculatorButton.setFont(font);
        menu.add(calculatorButton, constraints);
        calculatorButton.addActionListener((ActionEvent e) -> {
            //do something
            calculatorButton.setEnabled(false);
            CC_Calculator calculator = new CC_Calculator(width, height);

            // Add a WindowListener to enable the button when the new JFrame is closed
            calculator.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    calculatorButton.setEnabled(true);
                }
            });
        });

        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        JButton ProfileButton = new JButton("Profile");
        ProfileButton.setFont(font);
        menu.add(ProfileButton, constraints);
        ProfileButton.addActionListener((ActionEvent e) -> {
            //do something
            ProfileButton.setEnabled(false);
            Profile profile = new Profile(width, height, username);

            // Add a WindowListener to enable the button when the new JFrame is closed
            profile.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    ProfileButton.setEnabled(true);
                }
            });
        });

        constraints.gridx = 5;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        JButton LogoutButton = new JButton("Logout");
        LogoutButton.setFont(font);
        menu.add(LogoutButton, constraints);
        LogoutButton.addActionListener((ActionEvent e) -> {
            // todo: this should close all exist window
            menu.dispose();
            main.main(new String[0]);
        });

        // Text area to display submitted feedback
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea feedbackArea = new JTextArea(11, 30);
        feedbackArea.setEditable(false);
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 6;
        constraints.gridheight = 6;
        panel.setPreferredSize(new Dimension(width / 3, height / 3));

        Font panel_font = new Font("Arial", Font.PLAIN, width / 50);

        // Add the components to the frame
        JPanel bottom_panel = new JPanel(new BorderLayout());

        // Button to refresh comment panel
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(panel_font);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedbackArea.setText(new data().read_user_comment());
            }
        });

        bottom_panel.add(refreshButton, BorderLayout.WEST);

        // Button to submit feedback
        JButton submitButton = new JButton("Submit Comment");
        submitButton.setFont(panel_font);

        JLabel FeedbackLabel = new JLabel("Please enter your comment:");
        FeedbackLabel.setFont(panel_font);
        feedbackArea.setFont(panel_font);

        feedbackArea.setText(new data().read_user_comment());
        panel.add(new JScrollPane(feedbackArea), BorderLayout.CENTER);

        submitButton.addActionListener((ActionEvent e) -> {
            // Show an input dialog to get feedback from the user
            String feedback = JOptionPane.showInputDialog(menu, FeedbackLabel, "Comment", JOptionPane.PLAIN_MESSAGE);

            // Add the submitted feedback to the text area
            if (feedback != null && feedback.length() > 1) {
                feedbackArea.append(username + " :  " + feedback + "\n");
                new data().user_comment(feedbackArea.getText());
                feedbackArea.setText(new data().read_user_comment());
            }
        });

        bottom_panel.add(submitButton, BorderLayout.CENTER);

        // Adding a button for the admin 
        if (access.equals("admin")) {
            feedbackArea.setEditable(true);
            //button of edit
            JButton editbutton = new JButton("Save edit");
            editbutton.setFont(panel_font);

            editbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new data().user_comment(feedbackArea.getText());
                }
            });

            bottom_panel.add(editbutton, BorderLayout.SOUTH);
        }

        panel.add(bottom_panel, BorderLayout.SOUTH);
        menu.add(panel, constraints);

        menu.setVisible(true);
        menu.setLocationRelativeTo(null); // Center the frame on the screen
    }
}
