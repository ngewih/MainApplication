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
import javax.swing.JFrame;

public class Client {

    public static void main(String[] args) {
        ClientFile c;
        // Local host connection can be stored on a dedicated server.
        c = new ClientFile("127.0.0.1");
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.prepare();
        c.setVisible(true);

    }

}
