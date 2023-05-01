import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Interface {
    public static void main(String[] args) {
        JFrame f = new JFrame("Interface");

        JLabel label = new JLabel("File Info");
        label.setBounds(50, 10, 250, 100);

        JLabel label11 = new JLabel("File Name");
        label11.setBounds(50, 55, 100, 30);

        JLabel label12 = new JLabel("File Status");
        label12.setBounds(50, 85, 100, 30);

        JLabel label13 = new JLabel("Code");
        label13.setBounds(50, 115, 100, 30);

        JLabel label14 = new JLabel("Registers");
        label14.setBounds(50, 160, 100, 30);

        JLabel label15 = new JLabel("Next instruction");
        label15.setBounds(50, 190, 100, 30);


        JLabel label2 = new JLabel("Variables");
        label2.setBounds(400, 55, 100, 30);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(400, 80, 150, 80);

        JLabel label3 = new JLabel("Stack");
        label2.setBounds(400, 160, 100, 30);

        /*JTextArea textArea2 = new JTextArea();
        textArea.setBounds(400, 185, 150, 80);*/
        
        JButton button = new JButton("Load File");
        button.setBounds(400, 260, 150, 30);

        JButton button2 = new JButton("Check File");
        button2.setBounds(400, 300, 150, 30);

        JButton button3 = new JButton("Simulate");
        button3.setBounds(400, 340, 150, 30);

        JButton button4 = new JButton("Step Simulation");
        button4.setBounds(400, 380, 150, 30);

        f.add(label);
        f.add(label2);
        f.add(label11);
        f.add(label12);
        f.add(label13);
        f.add(label14);
        f.add(label15);
        f.add(textArea);
        f.add(label3);
        //f.add(textArea2);
        f.add(button);
        f.add(button2);
        f.add(button3);
        f.add(button4);

        f.setSize(700,500);
        f.setLayout(null);
        f.setVisible(true);
    }
}