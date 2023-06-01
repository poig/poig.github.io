

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author junli
 */
import user_function.quiz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class menu extends JPanel {
            
    public menu() {
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
        Font font = new Font("Arial", Font.PLAIN, width/40);
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
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
        
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        JButton NewsPaper = new JButton("Newspaper");
        NewsPaper.setFont(font);
        menu.add(NewsPaper, constraints);
        NewsPaper.addActionListener((ActionEvent e) -> {
            //do something
        });
        
        constraints.gridx = 5;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        JButton ArticleButton = new JButton("Article");
        ArticleButton.setFont(font);
        menu.add(ArticleButton, constraints);
        ArticleButton.addActionListener((ActionEvent e) -> {
            //do something
            
        });
        
       
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        JButton calculatorButton = new JButton("Carbon Consumption Calculator");
        calculatorButton.setFont(font);
        menu.add(calculatorButton, constraints);
        calculatorButton.addActionListener((ActionEvent e) -> {
            //do something
        });
        
        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        JButton ProfileButton = new JButton("Profile");
        ProfileButton.setFont(font);
        menu.add(ProfileButton, constraints);
        ProfileButton.addActionListener((ActionEvent e) -> {
            //do something
        });
        
        constraints.gridx = 5;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        JButton LogoutButton = new JButton("Logout");
        LogoutButton.setFont(font);
        menu.add(LogoutButton, constraints);
        LogoutButton.addActionListener((ActionEvent e) -> {
            //do something
            menu.dispose();
            main.main(new String[0]);
        });
        
        menu.setVisible(true);
        menu.setLocationRelativeTo(null); // Center the frame on the screen
    }
}