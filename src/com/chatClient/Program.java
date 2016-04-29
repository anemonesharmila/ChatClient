/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author sharmila
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the server address:");
        String host = input.next();
        System.out.println("Enter your port number:");
        int port = input.nextInt();
        try {
            Socket client = new Socket(host, port);
            Scanner reader = new Scanner(client.getInputStream());
            while (true) {
                doLogin(client, reader, input);
                

            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public static void sendMessage(Socket client,String message) throws IOException {
        PrintWriter writer = new PrintWriter(client.getOutputStream());
        writer.write(message+"\r\n");
        writer.flush();
    }

    private static void doLogin(Socket client, Scanner reader, Scanner input) throws IOException {

        while (true) {
            System.out.println(reader.nextLine());
            String userName = input.nextLine();
            sendMessage(client, userName);
            System.out.println(reader.nextLine());
            String password = input.nextLine();
            sendMessage(client, password);
            if (reader.nextLine().contains("Invalid username and password")) {
                break;
            }
        }

    }
}
