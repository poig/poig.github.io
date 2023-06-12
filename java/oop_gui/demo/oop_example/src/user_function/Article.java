package user_function;

import main.main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class Article {

    // add to main: public static String inp1, inp2, inp3;
    // add to main: public static int count=1;
    String[] files = {"articles/sustainable_fashion.txt", "articles/sustainable_living.txt", "articles/global_warming.txt"};
    String[] content = {"", "", ""};

    public void saveText(String f, String s) throws IOException {
        PrintWriter out = new PrintWriter(f);
        out.println(s);
        out.close();

    }

    JFrame f;

    public Article(int width, int height) throws IOException {

        if (!new File(files[0]).exists()) {
            File article_folder = new File(files[0]);
            article_folder.getParentFile().mkdirs();
        }

        StringBuffer buffer1 = new StringBuffer();
        RandomAccessFile r1 = new RandomAccessFile(new File(files[0]), "rw");
        while (r1.getFilePointer() < r1.length()) {
            buffer1.append(r1.readLine() + System.lineSeparator());
        }
        content[0] = buffer1.toString();

        StringBuffer buffer2 = new StringBuffer();
        RandomAccessFile r2 = new RandomAccessFile(new File(files[1]), "rw");
        while (r2.getFilePointer() < r2.length()) {
            buffer2.append(r2.readLine() + System.lineSeparator());
        }
        content[1] = buffer2.toString();

        StringBuffer buffer3 = new StringBuffer();
        RandomAccessFile r3 = new RandomAccessFile(new File(files[2]), "rw");
        while (r3.getFilePointer() < r3.length()) {
            buffer3.append(r3.readLine() + System.lineSeparator());
        }
        content[2] = buffer3.toString();

        if (main.count == 1) {
            main.inp1 = content[0];
            main.inp2 = content[1];
            main.inp3 = content[2];
            main.count++;
        }

        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        f.setSize(width, height);
        f.setLocationRelativeTo(null);

        f.setTitle("Articles");
        Font font = new Font("Arial", Font.PLAIN, width / 30);

        JTabbedPane tp = new JTabbedPane();
        JPanel ap = new JPanel(new BorderLayout());
        ap.setBounds(10, 10, 15, 50);

        int w = 13;
        int h = 45;
        JTextArea list1 = new JTextArea(w, h);
        list1.setFont(font);
        list1.setText(main.inp1);
        list1.setEditable(true);
        JScrollPane sp1 = new JScrollPane(list1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp1.setViewportView(list1);
        JTextArea list2 = new JTextArea(w, h);
        list2.setFont(font);
        list2.setText(main.inp2);
        list2.setEditable(true);
        JScrollPane sp2 = new JScrollPane(list2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp2.setViewportView(list2);
        JTextArea list3 = new JTextArea(w, h);
        list3.setFont(font);
        list3.setText(main.inp3);
        list3.setEditable(true);
        JScrollPane sp3 = new JScrollPane(list3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp3.setViewportView(list3);

        tp.add("article 1", sp1);
        tp.add("article 2", sp2);
        tp.add("article 3", sp3);
        tp.setFont(font);
        ap.add(tp, BorderLayout.NORTH);

        JButton back = new JButton("Back to Main Menu");
        back.setFont(font);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!list1.getText().equals(main.inp1) || !list2.getText().equals(main.inp2) || !list3.getText().equals(main.inp3)) {
                    main.inp1 = list1.getText();
                    main.inp2 = list2.getText();
                    main.inp3 = list3.getText();
                    try {
                        saveText(files[0], main.inp1);
                        saveText(files[1], main.inp2);
                        saveText(files[2], main.inp3);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    main.inp1 = main.inp1;
                    main.inp2 = main.inp2;
                    main.inp3 = main.inp3;
                }
                //f.setVisible(false);
                f.dispose();
            }
        });

        ap.setFont(font);
        ap.add(back, BorderLayout.WEST);
        f.add(ap);
        f.setVisible(true);
    }

    public void addWindowListener(WindowListener listener) {
        this.f.addWindowListener(listener);
    }

    public static void main(String[] args) throws IOException {
        new Article(1600, 600);
    }
}
