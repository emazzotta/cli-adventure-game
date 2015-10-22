package com.mazzotta.kuster.clickpoint.game.user_interface;

import com.mazzotta.kuster.clickpoint.game.commands.*;

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
                        System.out.println("Test2222");
                        gameOutput.setText("fewfaw");
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        };
    }
    // random comment
    private void initUI() {
        frame = new JFrame("Point & Click by Emanuele");
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize=new Dimension((int)(screenSize.width/2),(int)(screenSize.height/2));
        int x = (int)(frameSize.width/2);
        int y = (int)(frameSize.height/2);
        frame.setBounds(x, y, frameSize.width, frameSize.height);

        gameOutput = new JTextArea();
        gameOutput.setEditable(false);
        userInput = new JTextField();

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(gameOutput, BorderLayout.CENTER);
        frame.getContentPane().add(userInput, BorderLayout.SOUTH);

        userInput.addActionListener(action);

        frame.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    Action action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Queue.getInstance().addUserInput(actionEvent.getActionCommand());
            userInput.setText("");
        }
    };
}