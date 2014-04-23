/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Edgar
 */
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ClientFile extends JFrame {

    private JTextField userInput;
    private JTextArea chatArea;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private String message = "";
    private String ip;
    private Socket initializeConnection;

    public ClientFile(String host) {
        super("Client Version Early Alpha");
        ip = host;
        userInput = new JTextField();
        userInput.setEditable(false);
        userInput.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                send(e.getActionCommand());
                userInput.setText("");
            }
        });
        add(userInput, BorderLayout.SOUTH);
        chatArea = new JTextArea();
        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        setSize(1024, 683);
        setVisible(true);
    }

    // connect to server
    public void prepare() {
        try {

            connect();
            steams();
            chat();

        } catch (EOFException eof) {
            display("\nUser terminated the connection");
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            close();
        }

    }

    /**
     *
     * Connect to the server.
     */
    private void connect() throws IOException {
        display("Attempting to connect \n");
        // Send in to the port.
        initializeConnection = new Socket(InetAddress.getByName(ip), 9687);
        display("Connected to: \n" + initializeConnection.getInetAddress().getHostName());

    }

    /**
     *
     * Preparing the streams and the connection.
     */
    private void steams() throws IOException {
        outputStream = new ObjectOutputStream(initializeConnection.getOutputStream());
        outputStream.flush();
        inputStream = new ObjectInputStream(initializeConnection.getInputStream());
        display("\nConnection successfully established!");

    }

    // while in chat  with the sever.
    private void chat() throws IOException {
        type(true);
        do {
            try {
                message = (String) inputStream.readObject();
                display("\n" + message);
            } catch (ClassNotFoundException cnfe) {
                display("\n Invalid input!");

            }
        } while (!message.equalsIgnoreCase("Close"));
    }

    // close the application
    private void close() {
        display("\nLost connection with the server :(!");
        type(false);

        try {

            outputStream.close();
            inputStream.close();

            initializeConnection.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // send message to the server
    private void send(String message) {
        try {
            outputStream.writeObject("User:" + message);
            outputStream.flush();
            display("\nUser:" + message);
        } catch (IOException ioe) {
            chatArea.append("\n Offline please check the connection or contact a admin.");
        }
    }

    // change or update chat window
    private void display(final String messages) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                chatArea.append(messages);

            }
        });

    }

    // gives user permission to type.
    private void type(final boolean tof) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                userInput.setEditable(tof);
            }
        });

    }

}
