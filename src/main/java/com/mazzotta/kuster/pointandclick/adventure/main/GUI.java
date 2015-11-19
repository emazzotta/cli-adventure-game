package com.mazzotta.kuster.pointandclick.adventure.main;

import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.commands.parsing.InputParser;
import com.mazzotta.kuster.pointandclick.adventure.commands.parsing.exception.InvalidUserInputException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class GUI extends JFrame {

    private JFrame frame;
    private JTextArea gameOutput;
    private JTextField userInput;
    private Thread checkOutput;
    private static boolean running;

    public GUI() {
        initUI();
        running = false;
        checkOutput = getThread(gameOutput);
    }

    private Thread getThread(final JTextArea gameOutput) {
        return new Thread() {
            public void run() {
                running = true;
                while (running) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        };
    }

    // random comment
    private void initUI() {
        frame = new JFrame("Point & Click by Emanuele & Leandro");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension((screenSize.width / 2), (screenSize.height / 2));
        int x = (frameSize.width / 2);
        int y = (frameSize.height / 2);
        frame.setBounds(x, y, frameSize.width, frameSize.height);

        gameOutput = new JTextArea();
        gameOutput.setEditable(false);
        userInput = new JTextField();

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(gameOutput, BorderLayout.CENTER);
        frame.getContentPane().add(userInput, BorderLayout.SOUTH);

        userInput.addActionListener(action);

        frame.setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void updateGUI() {
        System.out.println("Input: " + Queue.getInstance().getPendingUserInput());
        System.out.println("Output: " + Queue.getInstance().getPendingGameOutput());
        for(String output : Queue.getInstance().getPendingGameOutput()) {
            gameOutput.append(output + "\n");
        }
    }

    Action action = new AbstractAction() {
        public void actionPerformed(ActionEvent actionEvent) {
            InputParser inputParser = new InputParser();
            try {
                System.out.println("ActionCommand from User input: " + actionEvent.getActionCommand());
                inputParser.createCommandActionFrom(actionEvent.getActionCommand());
            } catch (InvalidUserInputException e) {
                inputParser.createInvalidCommandActionFrom(actionEvent.getActionCommand());
                e.printStackTrace();
            }
            Queue.getInstance().addUserInput(inputParser.getCommandAction());
            userInput.setText("");
        }
    };
}
