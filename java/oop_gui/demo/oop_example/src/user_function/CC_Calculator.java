package user_function;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author poig
 */
public class CC_Calculator {

    JFrame calculator;
    JTextArea resultArea;
    JLabel label;

    public CC_Calculator(int width, int height) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Create JFrame
        calculator = new JFrame("Carbon Consumption Calculator");
        calculator.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // for user-log
        System.out.println("Enter profile");
        width = (int) (width * 0.5);
        height = (int) (height * 0.8);
        calculator.setSize(width, height);
        calculator.setTitle("Calculator"); // set title here

        calculator.setLayout(new GridBagLayout());
        // font-size
        Font font = new Font("Arial", Font.PLAIN, width / 25);

        // setup constrain
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        // Create JLabels, JTextFields
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        JLabel milesLabel = new JLabel("Miles driven per year:");
        milesLabel.setFont(font);
        calculator.add(milesLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        JTextField milesField = new JTextField();
        milesField.setFont(font);
        calculator.add(milesField, constraints);
        //milesField.setBounds(180, 20, 200, 20);
        calculator.add(label = new JLabel());
        label.setForeground(Color.red);
        milesField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = milesField.getText();
                //int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == '.' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    milesField.setEditable(true);
                    label.setText("");
                } else {
                    milesField.setEditable(false);
                    label.setText("* Enter only numeric digits(0-9)");
                }
            }
        });

        //
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        JLabel efficiencyLabel = new JLabel("Fuel efficiency (MPG):");
        efficiencyLabel.setFont(font);
        calculator.add(efficiencyLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        JTextField efficiencyField = new JTextField();
        efficiencyField.setFont(font);
        calculator.add(efficiencyField, constraints);
        efficiencyField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = efficiencyField.getText();
                //int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == '.' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    efficiencyField.setEditable(true);
                    label.setText("");
                } else {
                    efficiencyField.setEditable(false);
                    label.setText("* Enter only numeric digits(0-9)");
                }
            }
        });

        //
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        JLabel emissionsLabel = new JLabel("Carbon emissions factor (lbs/gal):");
        emissionsLabel.setFont(font);
        calculator.add(emissionsLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        JTextField emissionsField = new JTextField();
        emissionsField.setFont(font);
        calculator.add(emissionsField, constraints);
        emissionsField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = emissionsField.getText();
                //int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == '.' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    emissionsField.setEditable(true);
                    label.setText("");
                } else {
                    emissionsField.setEditable(false);
                    label.setText("* Enter only numeric digits(0-9)");
                }
            }
        });

        //
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(font);
        calculator.add(calculateButton, constraints);
        //calculateButton.setBounds(100, 120, 200, 30);

        // Create JTextArea
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 3;
        constraints.gridheight = 3;
        resultArea = new JTextArea();
        resultArea.setFont(font);
        //resultArea.setBounds(20, 160, 360, 80);
        resultArea.setEditable(false);
        calculator.add(resultArea, constraints);
        resultArea.setPreferredSize(new Dimension(400, 300));
        set_result(0, 0, 0, 0, 0);

        // Calculate button action
        calculateButton.addActionListener(e -> {
            try{
                double milesDriven = Double.parseDouble(milesField.getText());
                double fuelEfficiency = Double.parseDouble(efficiencyField.getText());
                double carbonEmissionsFactor = Double.parseDouble(emissionsField.getText());
                
                double gallonsConsumed = milesDriven / fuelEfficiency;
                double carbonConsumed = gallonsConsumed * carbonEmissionsFactor;

                set_result(milesDriven, fuelEfficiency, carbonEmissionsFactor, gallonsConsumed, carbonConsumed);
            } catch (NumberFormatException ee){
                label.setText("* Enter only numeric floating digits(0-9)");
            }

                
            
            });

        // Set the JFrame visible
        calculator.setVisible(true);
            calculator.setLocationRelativeTo(null); // Center the frame on the screen
        }

    public void addWindowListener(WindowListener listener) {
        this.calculator.addWindowListener(listener);
    }

    private void set_result(double milesDriven, double fuelEfficiency, double carbonEmissionsFactor, double gallonsConsumed, double carbonConsumed) {
        String result = "Miles driven per year: " + milesDriven
                + "\nFuel efficiency: " + fuelEfficiency + " miles per gallon"
                + "\nCarbon emissions factor: " + carbonEmissionsFactor + " pounds per gallon"
                + "\nGallons consumed per year: " + gallonsConsumed
                + "\nCarbon consumed per year: " + carbonConsumed + " pounds";

        resultArea.setText(result);
    }

    public static void main(String[] args) {
        new CC_Calculator(1280, 720);
    }
}
