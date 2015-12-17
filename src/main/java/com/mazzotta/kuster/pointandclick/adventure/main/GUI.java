package com.mazzotta.kuster.pointandclick.adventure.main;

import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;
import com.mazzotta.kuster.pointandclick.adventure.commands.History;
import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.commands.parsing.InputParser;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class GUI extends JFrame {

    private JTextArea gameOutput;
    private JTextField userInput;


    public GUI() {
        initUI();
    }

    private void initUI() {
        JFrame frame = new JFrame("Point & Click by Emanuele & Leandro");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension((screenSize.width / 2), (screenSize.height / 2));
        int x = frameSize.width / 2;
        int y = frameSize.height / 2;
        frame.setBounds(x, y, frameSize.width, frameSize.height);

        gameOutput = new JTextArea();
        gameOutput.setEditable(false);
        userInput = new JTextField();
        JScrollPane gameScrollWindow = new JScrollPane(gameOutput);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(gameScrollWindow, BorderLayout.CENTER);
        frame.getContentPane().add(userInput, BorderLayout.SOUTH);

        userInput.addActionListener(action);
        userInput.addKeyListener(keyListener);

        frame.setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void updateGUI() {
        gameOutput.setText("");
        gameOutput.append(statistics());
        for (String output : Queue.getInstance().getPendingGameOutput()) {
            gameOutput.append(output);
            gameOutput.append("\n");
        }
    }

    private String statistics() {
        return "###############################\n" +
                "Statistics:\n" +
                "Health: " + State.getInstance().getPlayer().getHealth() + "\n" +
                "Attack Strength: " + State.getInstance().getPlayer().getAttackPoints() + "\n" +
                "Current Weapon: " + State.getInstance().getPlayer().getEquippedWeapon().getName() + "\n" +
                "Amount of Potions: " + State.getInstance().getPlayer().getInventory().getPotions().size() + "\n" +
                "Current Room: " + State.getInstance().getCurrentRoom().getName() + "\n" +
                "###############################\n\n";
    }

    Action action = new AbstractAction() {
        public void actionPerformed(ActionEvent actionEvent) {
            CommandAction commandAction = new InputParser().getCommandActionFrom(actionEvent.getActionCommand());
            Queue.getInstance().addUserInput(commandAction);
            userInput.setText("");
        }
    };

    KeyListener keyListener = new KeyListener() {
        int commandIndex = 0;

        public void keyPressed(KeyEvent keyEvent) {
            switch (KeyEvent.getKeyText(keyEvent.getKeyCode())) {
                case "↑":
                    try {
                        commandIndex++;
                        setInputFieldToLastCommandBasedOnCurrentIndex();
                    } catch (Exception e) {
                        commandIndex--;
                        setInputFieldToLastCommandBasedOnCurrentIndex();
                    }
                    return;
                case "↓":
                    try {
                        commandIndex--;
                        setInputFieldToLastCommandBasedOnCurrentIndex();
                    } catch (Exception e) {
                        commandIndex++;
                        setInputFieldToLastCommandBasedOnCurrentIndex();
                    }
                    return;
                case "⏎":
                    commandIndex = 0;
            }
        }

        public void keyReleased(KeyEvent keyEvent) {
        }

        public void keyTyped(KeyEvent keyEvent) {
        }

        private void setInputFieldToLastCommandBasedOnCurrentIndex() {
            ArrayList<CommandAction> enteredCommands = History.getInstance().getEnteredCommands();
            userInput.setText(enteredCommands.get(enteredCommands.size() - commandIndex).toString());
        }
    };
}