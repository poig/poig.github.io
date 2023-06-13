package demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileExample {
    public static void main(String[] args) {
        File file = new File("C:\\\\Users\\\\poig\\\\Documents\\\\GitHub\\\\poig.github.io\\\\java\\\\oop_gui\\\\demo\\\\oop_example\\\\dist\\\\user_dictionary.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}