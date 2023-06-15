package user_function;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author poig
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class quiz extends JPanel {

    // Declare variables
    //JLabel question;
    ButtonGroup optionsGroup;
    JButton nextButton;
    JButton hintButton;
    int currentQuestion = 0;
    int ques_type = 0;
    int grid_pos;
    int score = 0;
    GridBagConstraints constraints;
    Font font;
    JButton quesButton;

    // Array of questions
    String[][][] questions = {
        // sustainable Fashion
        // question 1
        {{"Do you know what sustainable fashion is?",
            // option 1
            "It is fashion that is ethically made and environmentally friendly.",
            // option 2
            "It is a kind of fashion that will become outdated in a short time.",
            // option 3
            "It is a kind of fashion that is very lucrative.",},
        // question 2
        {"What percentage of total global greenhouse gas emissions are produced by the fashion industry?",
            "Less than 1%", "4%", "10%", "15%"},
        // question 3
        {"Do you know what Sustainable Development Goals (SDGs) is related to sustainable fashion?",
            "SDG 12: Responsible Consumption and Production",
            "SDG 7: Affordable and Clean Energy",
            "SDG 9: Industry, Innovation and Infrastructure"},
        // question 4
        {"The fashion industry contributes to environmental degradation in which of the following ways?",
            "Deforestation",
            "Desertification and degradation of soil",
            "Loss of biodiversity",
            "All of the above"},
        // question 5
        {"How many litres of water does it take to make a pair of jeans?",
            "52 liters",
            "476 liters",
            "1028 liters",
            "3781 liters"},
        // question 6
        {"Do you know which is the following brand implementing sustainable fashion concepts?",
            "Uniqlo",
            "H&M",
            "Zara",
            "Adidas"},
        // question 7
        {"Do you know why sustainable fashion is important? Select the wrong answer of the importance of sustainable fashion.",
            "Reduced waste generation",
            "Fair wages and healthy work environment",
            "Reduced carbon dioxide and greenhouse gases emissions",
            "Makes people feel more comfortable about the clothes they are wearing"},
        // question 8
        {"Instead of throwing out unwanted clothing and contributing to textile waste, what can you do instead?",
            "Donate them to charities and shelters",
            "Repurpose them as rags",
            "repair or alter them into something wearable, like cutting jeans into shorts",
            "All of the above"},
        // question 9
        {"What percentage of clothing is recycled?",
            "50%",
            "33%",
            "10%",
            "Less than 1 %"},
        // question 10
        {"What does greenwashing in fashion refer to?",
            "Washing clothing in environmentally friendly ways",
            "Claims of environmental sustainability by a brand that are false or misleading",
            "Using natural fabric dyes",},},
        //Global Warming
        //question 1
        {{"Global warming is a consequence of ____",
            //option 1
            "Acid rain",
            //option 2
            "Greenhouse effect",
            //option 3
            "Depletion of the ozone layer",
            //option 4
            "Radioactive fall out"},
        //question 2
        {"Where have some of the strongest and earliest impact of global warming occurred?",
            //option 1
            "In the tropics",
            //option 2
            "In northern latitudes",
            //option 3
            "Impacts of global warming are distributed equally all over the planet",
            //option 4
            "All of the above"},
        //question 3
        {"Most places on Earth are warmer than they were 100 years ago.",
            //option 1
            "True",
            //option 2
            "False"},
        //question 4
        {"How do scientists collect evidence about climate?",
            //option 1
            "Using remote sensing from space with satelities",
            //option 2
            "By ground-based measurements of surface temperature, carbon dioxide concentration and sea level.",
            //option 3
            "By collecting 'proxy data' from tree rings, ice cores and historical records",
            //option 4
            "All of the above"},
        //question 5
        {"Some kinds of pollution in the atmosphere can act to cool the planet by reducing the amount of solar radiation that reaches Earth's surface.",
            //option 1
            "True",
            //option 2
            "False"},
        //question 6
        {"If you removed the atmosphere's natural greenhouse effect, and everything else stayed the same, Earth's temperature would be:",
            //option 1
            "10 to 20'F(6 to 11'C)warmer",
            // option 2
            "30 to 40'F(17 to 22'C)warmer",
            //option 3
            "10 to 20'F(6 to 11'C)cooler",
            //option 4
            "50 to 60'F(28 to 33'C)cooler"},
        //question 7
        {"Which of the following gases does not trap heat?",
            //option 1
            "Carbon dioxide",
            //option 2
            "Nitrogen",
            //option 3
            "Water vapor",
            //option 4
            "Methane"},
        //question 8
        {"Today's warming is unprecedented in the last:",
            //option 1
            "100 years",
            //option 2
            "500 years",
            //option 3
            "1,000 years",
            //option 4
            "2,000 years"},
        //question 9
        {"As average global temperature rises,",
            //option 1
            "Average precipitation increases",
            //option 2
            "Average precipitation decreases",
            //option 3
            "Average precipitation is unchanged"},
        //question 10
        {"Which greenhouse gas is the main driver of Earht's current warming?",
            //option 1
            "Methane",
            //option 2
            "Carbon dioxide",
            //option 3
            "Nitrous oxide",
            //option 4
            "Water vapor"},},
        //Global Warming
        //question 1
        {{"What interpretation can we attribute to the concept of a sustainable lifestyle?",
            //option 1
            "A method of increasing one's carbon footprint.",
            //option 2
            "A method of conserving non-renewable resources.",
            //option 3
            "A method of promoting pollution and environmental degradation",
            //option 4
            "A method of reducing one’s carbon footprint."},
        //question 2
        {"Which is NOT a way to decrease your carbon footprint?",
            //option 1
            "Use the cold water cycle for washing your clothes",
            //option 2
            "Eat more food that is grown or made locally and less red meat.",
            //option 3
            "Incorporate walking or biking to some of your regular short-trip destinations.",
            //option 4
            "Continue switching on lights and unplug devices when you’re not using them."},
        //question 3
        {"What is the role of the \"Malaysian Green Technology and Climate Change Centre\"?",
            //option 1
            "To promote sustainable tourism",
            //option 2
            "To regulate air pollution",
            //option 3 
            "To develop renewable energy technologies",
            //option 4
            "To support logging activities"},
        //question 4         
        {"Which Sustainable Development Goal is NOT included in practicing sustainable lifestyles?",
            //option 1
            "SDG 1: No Poverty",
            //option 2
            "SDG 13: Climate action",
            //option 3
            "SDG 12.8: Responsible Consumption",
            //option 4
            "SDG 4: Education"},
        //question 5
        {"How can we incorporate sustainable living into our daily lives in Malaysia?",
            //option 1
            "Natural clean personal care to avoid toxins.",
            //option 2
            "Buy less and focus on quality.",
            //option 3
            "Reduce purchase of bottled water and polystyrene lunch box, carry your own reusable tumblers and food containers.",
            //option 4
            "All of above"},
        //question 6
        {"Which of the following is an energy-efficient lighting option?",
            //option 1
            "Incandescent bulbs",
            // option 2
            "Compact fluorescent bulbs",
            //option 3
            "Halogen bulbs",
            //option 4
            "Neon bulbs"},
        //question 7
        {"What is the largest source of marine pollution in Malaysia?",
            //option 1
            "Industrial waste",
            //option 2
            "Sewage discharge",
            //option 3
            "Plastic pollution",
            //option 4
            "Oil spills"},
        //question 8
        {"What is the main contributor to air pollution in urban areas of Malaysia?",
            //option 1
            "Industrial emissions",
            //option 2
            "Vehicle exhaust emissions",
            //option 3
            "Construction activities",
            //option 4
            "Agricultural activities"},
        //question 9
        {"What is the purpose of the Green Building Index (GBI) in Malaysia?",
            //option 1
            "To promote sustainable tourism",
            //option 2
            "To regulate water usage in buildings",
            //option 3
            "To certify and rate the environmental performance of buildings",
            //option 4
            "To enforce energy efficiency standards in construction"},
        //question 10
        {"What is the main cause of deforestation in Malaysia?",
            //option 1
            "Urbanization",
            //option 2
            "Mining activities",
            //option 3
            "Agricultural expansion",
            //option 4
            "Industrial pollution"},
        //question 11
        {"Which of the following is a sustainable practice for reducing food waste in Malaysia?",
            //option 1
            "Buying excessive amounts of food",
            //option 2
            "Composting food waste",
            //option 3
            "Throwing away edible leftovers",
            //option 4
            "Ignoring expiration dates"},
        //question 12
        {"Which sector is the largest contributor to greenhouse gas emissions in Malaysia?",
            //option 1
            "Energy",
            //option 2
            "Transportation",
            //option 3
            "Agriculture",
            //option 4
            "Industry"},
        //question 13
        {"Which of the following is a sustainable method for pest control in agriculture?",
            //option 1 
            "Integrated Pest Management (IPM) techniques",
            //option 2
            "Introduction of genetically modified organisms (GMOs)",
            //option 3
            "Use of synthetic fertilizers",
            //option 4
            "Heavy reliance on chemical pesticides"},
        //question 14
        {"Which of the following is a sustainable practice for preserving Malaysia's cultural heritage?",
            //option 1
            "Encouraging the construction of modern buildings in heritage sites",
            //option 2
            "Promoting responsible tourism and cultural preservation",
            //option 3
            "Neglecting the conservation of historical landmarks",
            //option 4
            "Allowing unregulated commercial development near heritage sites"},
        //question 15
        {"What is the term used to describe the integration of sustainability principles into educational curiculum?",
            //option 1
            "Sustainable education",
            //option 2
            "Eco-education",
            //option 3
            "Green curriculum",
            //option 4
            "Environmental studies"}}
    };

    String[][] answers = {
        // answer for the sustainable Fashion
        // answer for question 1
        {"It is fashion that is ethically made and environmentally friendly.",
            // answer for quesiton 2
            "4%",
            // answer for question 3
            "SDG 12: Responsible Consumption and Production",
            //q4
            "All of the above",
            //q5
            "3781 liters",
            //q6
            "Adidas",
            //q7
            "Makes people feel more comfortable about the clothes they are wearing",
            //q8
            "All of the above",
            //q9
            "Less than 1 %",
            //q10
            "Claims of environmental sustainability by a brand that are false or misleading"},
        //answer for the global warming
        //answer for question 1
        {"Greenhouse effect",
            //answer for question 2
            "In northern latitudes",
            //answer for question 3
            "True",
            //answer for question 4
            "All of the above",
            //answer for question 5
            "True",
            //answer for question 6
            "50 to 60'F(28 to 33'C)cooler",
            //answer for question 7
            "Nitrogen",
            //answer for question 8
            "2,000 years",
            //answer for question 9
            "Average precipitation increases",
            //answer for question 10
            "Carbon dioxide"},
        //answer for sustainable lifestyle
        //answer for question 1
        {"A method of reducing one’s carbon footprint.",
            //answer for question 2
            "Continue switching on lights and unplug devices when you’re not using them.",
            //answer for question 3
            "To develop renewable energy technologies",
            //answer for question 4
            "SDG 1: No Poverty",
            //answer for question 5
            "All of above",
            //answer for question 6
            "Compact fluorescent bulbs",
            //answer for question 7
            "Plastic pollution",
            //answer for question 8
            "Vehicle exhaust emissions",
            //answer for question 9
            "To certify and rate the environmental performance of buildings",
            //answer for question 10
            "Agricultural expansion",
            //answeer for question 11
            "Composting food waste",
            //answer for question 12
            "Energy",
            //answer for question 13
            "Integrated Pest Management(IPM)techniques",
            //answer for question 14
            "Promoting responsible tourism and culturaal preservation",
            //answser for question 15
            "Green curriculum"}
    };

    String[][] hints = {
        // explaination for the sustainable Fashion
        // explanation for question 1
        {"Sustainable fashion is a way in which brands create clothing that not only reduces the impact on the environment but is also mindful of the people who work to produce the garments.",
            // explanation for question 2
            "The fashion industry is responsible for at least 4% of global greenhouse gas emissions - more than the carbon output of the economies of France, Germany and the United Kingdom combined. ",
            //q3
            "One of the most important SDGs for the fashion industry is SDG 12, which calls for the decoupling of economic growth from increasing resource consumption.",
            //q4
            "Deforestation occurs when land is cleared for grazing animals that produce leather, wool, and other animal products. Deforestation and overgrazing can cause desertification and soil degradation. With this habitat loss and pollution from production, the fashion industry causes a loss of biodiversity as well.",
            //q5
            "It takes 3,781 liters of water to make one pair of jeans, from the production of the cotton to the delivery of the finished garment to a store. Textile manufacturing uses 20% of the world’s clean water each year.",
            //q6
            "Adidas have been increasing their sustainability and usage of recycled materials. The brand aims to only use recycled polyester from 2024 onwards to decrease their effect on the environment. Adidas are also set on reducing water consumption in production, 20% of the energy used is renewable, the company is also committed to fair labor practices.",
            //q7
            "Sustainable fashion aims to having an impact on protect our mother earth from global warming and also protect human rights.",
            //q8
            "All of the above are good alternatives to throwing out unwanted clothing. Just because a piece is no longer your size or style doesn’t mean it won’t be someone else’s, and there are so many ways to upcycle your old clothes into something new!",
            //q9
            "Less than 1% of clothing is recycled. The repurposing of textiles is often incorrectly referred to as recycling. They are actually reused by being broken down and made into new products, often insulation or stuffing materials.",
            //q10
            "Greenwashing refers to claims of environmental sustainability by a brand that are false or misleading. "},
        //explanation for the global warming
        //explanation for question 1
        {"Human activities, particularly the burning of fossil fuels (such as coal, oil, and natural gas) and deforestation, have significantly increased the concentrations of greenhouse gases in the atmosphere. This increase in greenhouse gases intensifies the greenhouse effect, leading to global warming.",
            //explanation for question 2
            "Some of the fastest-warming regions on the planet include Alaska, Greenland and Siberia. These Arctic environments are highly sensitive to even small temperature increases, which can melt sea ice, ice sheets and permafrost, and lead to changes in Earth's reflectance 'albedo'.",
            //explanation for question 3
            "Although most locations on the planet have recorded increased temperatures since 1880, changes in global ocean and atmospheric circulation patterns have created small-scale temperature decreases in a few local regions.",
            //explanation for question 
            "For the past few decades, scientists have had the benefit of global satellite data. We have accurate ground-based measurements that reach back just over a century. 'Proxy' methods, such as tree ring and ice core analysis, are used to reconstruct climate records before the rise of modern instruments.",
            //explanation for question 5
            "Air pollution can take the form of fine particles called 'aerosols' which both absorb and scatter the sun's radiation. Both natural and man-made aerosols, such as dust, sea salt, soot and sulphates, affect the climate by reflecting radiation that is transmitted through the atmosphere.",
            //explanation for question 6
            "The greenhouse effect is a naturally occurring physical process that warms the Earth's surface with energy from the atmosphere. Without the effect, Earth's average surface temperature would be well below freezing.",
            //explanation for question 7
            "Heat-trapping greenhouse gases absorb and emit radiation within the thermal infrared range. Water vapor, carbon dioxide and methane are Earth's most abundant greenhouse gases. Nitrogen, which makes up 80 percent of Earth's atmosphere, is not a greenhouse gas. This is because its molecules, which contain two atoms of the same element (nitrogen), are unaffected by infrared light.",
            //explanation for question 8
            "Although some think that the 'Medieval Warm Period' (approx. 800 – 1200 AD) was a global phenomenon, recent studies, including one by Neukom et al. 2019, show that there is no evidence that there were uniform warm and cold periods on Earth over the last 2,000 years.",
            //explanation for question 9
            "Higher temperatures give rise to a more active water cycle, which means faster and greater evaporation and precipitation and more extreme weather events.",
            //explanation for question 10
            "Some people mistakenly believe water vapor is the main driver of Earth’s current warming. But increased water vapor doesn’t cause human-produced global warming. Instead, it’s a consequence of it. Increased water vapor in the atmosphere supercharges the warming caused by other greenhouse gases, such as carbon dioxide, followed by methane."},
        //for sustainable lifestye

        // hints for question 1
        {"Carbon footprint: an environmental indicator measuring greenhouse gas emissions, expressed as CO2 equivalents, from specific activities.",
            //hints for question 2
            "Carbon footprint's importance: safeguards environment, cuts CO2 emissions, conserves resources, boosts local economy through sustainable practices.",
            //hints for question 3
            "MGTC fosters renewable energy development in Malaysia, promoting green technology adoption to combat climate change and drive sustainable growth.",
            //hints for question 4
            "SDGs: Transforming our world, ending poverty and inequality, protecting the planet, promoting health, justice, and prosperity for all. No one left behind.",
            //hints for question 5
            "Embrace zero waste: consciously replace daily items to minimize excessive waste, particularly plastic waste. Practice the 5 R's: refuse, reduce, reuse, repurpose, and recycle. Use reusable Beeswax wraps to reduce single-use plastic wrap and support local art.",
            //hints for question 6
            "Compact fluorescent bulbs: energy-efficient option consuming less electricity than incandescent bulbs. Halogen and neon bulbs are less efficient.",
            //hints for question 7
            "Plastic pollution dominates Malaysia's marine pollution, arising from single-use plastics, microplastics, and plastic debris, harming marine ecosystems.",
            //hints for question 8
            "Vehicle exhaust, especially from motor vehicles, is the primary cause of urban air pollution in Malaysia, impacting health and air quality due to traffic congestion.",
            //hints for question 9
            "Green Building Index (GBI) certifies buildings in Malaysia based on environmental performance, promoting sustainable practices and technologies in construction.",
            //hints for question 10
            "Agricultural expansion, mainly for oil palm plantations, drives deforestation in Malaysia, with significant environmental consequences.",
            //hints for question 11
            "Composting food waste in Malaysia reduces waste and promotes environmental conservation through sustainable practices.",
            //hints for question 12
            "The energy sector is the largest contributor to greenhouse gas emissions in Malaysia, primarily due to the use of fossil fuels.",
            //hints for question 13
            "IPM: Sustainable agriculture approach combining strategies for pest management, focusing on prevention, control, and environmental safety.",
            //hints for question 14
            "Responsible tourism preserves Malaysia's cultural heritage by raising awareness, promoting respectful behavior, and supporting conservation initiatives.",
            //hints for question 15
            "Green curriculum refers to the incorporation of sustainability principles and environmental awareness into educational programs, ensuring students learn about sustainable practices and develop a sense of environmental responsibility."}
    };

    int score_lenght = questions[ques_type].length;

    private final JFrame quiz;

    public quiz(int width, int height) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        quiz = new JFrame();
        quiz.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // for user-log
        System.out.println("Enter quiz");
        width = (int) (width * 0.5);
        height = (int) (height * 0.5);
        quiz.setSize(width, height);
        quiz.setTitle("quiz select"); // set title here

        quiz.setLayout(new GridBagLayout());
        // font-size
        font = new Font("Arial", Font.PLAIN, width / 30);

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;

        // first button
        quesButton = new JButton("fashion");
        quesButton.setFont(font);
        quiz.add(quesButton, constraints);
        quesButton.addActionListener((ActionEvent e) -> {
            ques_type = 0;
            quiz.getContentPane().removeAll();
            QuizPanel();
            quiz.revalidate();
            quiz.repaint();
        });

        //second button
        constraints.gridx = 2;
        constraints.gridy = 0;
        quesButton = new JButton("Global Warming");
        quesButton.setFont(font);
        quiz.add(quesButton, constraints);
        quesButton.addActionListener((ActionEvent e) -> {
            ques_type = 1;
            quiz.getContentPane().removeAll();
            QuizPanel();
            quiz.revalidate();
            quiz.repaint();
        });

        //second button
        constraints.gridx = 3;
        constraints.gridy = 0;
        quesButton = new JButton("Sustainable Lifestyle");
        quesButton.setFont(font);
        quiz.add(quesButton, constraints);
        quesButton.addActionListener((ActionEvent e) -> {
            ques_type = 2;
            quiz.getContentPane().removeAll();
            QuizPanel();
            quiz.revalidate();
            quiz.repaint();
        });

        quiz.setVisible(true);
        quiz.setLocationRelativeTo(null); // Center the frame on the screen

        // move into the quiz panel
        //quiz.getContentPane().removeAll();
    }

    public void RepaintQuestion() {
        // Initialize components
        grid_pos = 0;

        // question component
        JTextArea question = new JTextArea(questions[ques_type][currentQuestion][0]);
        question.setFont(font);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        JScrollPane scrollPane = new JScrollPane(question);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        quiz.getContentPane().add(scrollPane);
        quiz.add(question, constraints);
        grid_pos++;

        // answer component
        optionsGroup = new ButtonGroup();
        int i = 1;
        do {
            //for (int i = 1; i < questions[ques_type][currentQuestion].length; i++) {
            JRadioButton option = new JRadioButton(questions[ques_type][currentQuestion][i]);
            option.setFont(font);
            constraints.gridy = grid_pos;
            quiz.add(option, constraints);

            option.setActionCommand(questions[ques_type][currentQuestion][i]);
            optionsGroup.add(option);
            grid_pos++;
            i++;
        } while (i < questions[ques_type][currentQuestion].length);

        // next button component
        nextButton = new JButton("Submit");
        nextButton.setFont(font);
        constraints.gridy = grid_pos;
        quiz.add(nextButton, constraints);
        grid_pos++;

        // hin button component
        hintButton = new JButton("Hints");
        hintButton.setFont(font);
        constraints.gridy = grid_pos;
        quiz.add(hintButton, constraints);

        // Add action listener to next button
        // nextButton.addActionListener((ActionListener) this);
        nextButton.addActionListener((ActionEvent e) -> {
            // display registration screen
            if (optionsGroup.getSelection() == null) {
                JLabel empty_msg = new JLabel("Please select your answer");
                empty_msg.setFont(font);
                JOptionPane.showMessageDialog(this, empty_msg);
            } else {
                if (optionsGroup.getSelection().getActionCommand().equals(answers[ques_type][currentQuestion])) {
                    JLabel correct_msg = new JLabel("correct!");
                    correct_msg.setFont(font);
                    JOptionPane.showMessageDialog(quiz, correct_msg);
                    score++;
                } else {
                    JLabel wrong_msg = new JLabel("Incorrect answer, " + "\n" + "the answer is " + answers[ques_type][currentQuestion]);
                    wrong_msg.setFont(font);
                    JOptionPane.showMessageDialog(quiz, wrong_msg);
                }
                currentQuestion++;
                if (currentQuestion < score_lenght) {
                    quiz.getContentPane().removeAll();
                    RepaintQuestion();
                    quiz.revalidate();
                    quiz.repaint();
                } else {
                    JLabel end_msg = new JLabel("End of quiz!\n" + "your score: " + score);
                    JOptionPane.showMessageDialog(quiz, end_msg);

                    if ((score / score_lenght) > (score * 0.6) / score_lenght) {
                        // record badget to the user accordingly

                    }

                    quiz.dispose();
                }
                optionsGroup.clearSelection();
            }
        });

        hintButton.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(quiz, hints[ques_type][currentQuestion]);
        });
        quiz.pack();
    }

    public void QuizPanel() {
        RepaintQuestion();

        // Set frame properties
        setSize(400, 400);
        setVisible(true);
    }

    public void addQuizWindowListener(WindowListener listener) {
        this.quiz.addWindowListener(listener);
    }

}
