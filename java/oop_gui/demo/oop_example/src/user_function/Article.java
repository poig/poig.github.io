package user_function;

import main.main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.Map;

public class Article {

    // add to main: public static String inp1, inp2, inp3;
    // add to main: public static int count=1;
    URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
    String jarPath = url.getPath();
    File jarFile = new File(jarPath);
    String parentDir = jarFile.getParent();
    String[] files = {
        // first
        parentDir + "/articles/global_warming.txt", parentDir + "/articles/sustainable_living.txt", parentDir + "/articles/sustainable_fashion.txt"};
    String[] content = {"", "", ""};
    String[] addcontent = {"Climate change is a long-term change in the average weather patterns that have come to define Earth’s local, regional and global climates. These changes have a broad range of observed effects that are synonymous with the term.\n"
        + "\n"
        + "Changes observed in Earth’s climate since the mid-20th century are driven by human activities, particularly fossil fuel burning, which increases heat-trapping greenhouse gas levels in Earth’s atmosphere, raising Earth’s average surface temperature. Natural processes, which have been overwhelmed by human activities, can also contribute to climate change, including internal variability (e.g., cyclical ocean patterns like El Niño, La Niña and the Pacific Decadal Oscillation) and external forcings (e.g., volcanic activity, changes in the Sun’s energy output, variations in Earth’s orbit).\n"
        + "\n"
        + "Scientists use observations from the ground, air, and space, along with computer models, to monitor and study past, present, and future climate change. Climate data records provide evidence of climate change key indicators, such as global land and ocean temperature increases; rising sea levels; ice loss at Earth’s poles and in mountain glaciers; frequency and severity changes in extreme weather such as hurricanes, heatwaves, wildfires, droughts, floods, and precipitation; and cloud and vegetation cover changes.",
        // second
        "Sustainable living is a practical philosophy that aims to reduce personal and societal environmental impact by making positive changes which counteract climate change and other negative environmental concerns.\n"
        + "\n"
        + "More simply, sustainable living is a method of reducing one’s “carbon footprint”. If you need motivation to jump on board, try out the World Wildlife Fund’s environmental footprint calculator. \n"
        + "\n"
        + "“Sustainable living” encourages people to minimize their use of Earth’s resources and reduce the damage of human and environmental interactions." // third
        ,
         "Few industries tout their sustainability credentials more forcefully than the fashion industry. Products ranging from swimsuits to wedding dresses are marketed as carbon positive, organic, or vegan while yoga mats made from mushrooms and sneakers from sugar cane dot retail shelves. New business models including recycling, resale, rental, reuse, and repair are sold as environmental life savers.\n"
        + "\n"
        + "The sad truth however is that all this experimentation and supposed “innovation” in the fashion industry over the past 25 years have failed to lessen its planetary impact — a loud wake up call for those who hope that voluntary efforts can successfully address climate change and other major challenges facing society.\n"
        + "\n"
        + "Take the production of shirts and shoes, which has more than doubled in the past quarter century — three quarters end up burned or buried in landfills. This feels like a personal failure of sorts. For many years, I was the COO of Timberland, a footwear and apparel brand that aspired to lead the industry toward a more sustainable future. The reasons for the industry’s sustainability letdown are complicated. Pressure for unrelenting growth summed with consumer demand for cheap, fast fashion have been a major contributors.  So too are the related facts that real prices for footwear and apparel have halved since 1990 with most new items made from non-biodegradable petroleum-based synthetics."};

    public void saveText(String f, String s) throws IOException {

        try (FileWriter writer = new FileWriter(f, true);) {
            writer.write(s);
        }
        System.out.println("saved");

    }

    JFrame f;

    public Article(int width, int height, String access) throws IOException {

        if (!new File(files[0]).exists()) {
            File article_folder = new File(files[0]);
            article_folder.getParentFile().mkdirs();
            main.inp1 = addcontent[0];
            main.inp2 = addcontent[1];
            main.inp3 = addcontent[2];
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

        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        f.setSize(width, height);
        f.setLocationRelativeTo(null);

        f.setTitle("Articles");
        Font font = new Font("Arial", Font.PLAIN, width / 40);

        JTabbedPane tp = new JTabbedPane();
        JPanel ap = new JPanel(new BorderLayout());
        ap.setBounds(10, 10, 15, 50);

        int w = 13;
        int h = 30;
        JTextArea list1 = new JTextArea(w, h);
        list1.setFont(font);
        list1.setLineWrap(true);
        list1.setWrapStyleWord(true);
        list1.setText(main.inp1);
        list1.setEditable(false);
        JScrollPane sp1 = new JScrollPane(list1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp1.setViewportView(list1);
        JTextArea list2 = new JTextArea(w, h);
        list2.setFont(font);
        list2.setLineWrap(true);
        list2.setWrapStyleWord(true);
        list2.setText(main.inp2);
        list2.setEditable(false);
        JScrollPane sp2 = new JScrollPane(list2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp2.setViewportView(list2);
        JTextArea list3 = new JTextArea(w, h);
        list3.setFont(font);
        list3.setLineWrap(true);
        list3.setWrapStyleWord(true);
        list3.setText(main.inp3);
        list3.setEditable(false);
        JScrollPane sp3 = new JScrollPane(list3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp3.setViewportView(list3);

        if ("admin".equals(access)) {
            list1.setEditable(true);
            list2.setEditable(true);
            list3.setEditable(true);
        }

        tp.add("Global Warming", sp1);
        tp.add("Sustainable Living", sp2);
        tp.add("Sustainable Fashion", sp3);
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
                        System.out.print(ex);
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

}
