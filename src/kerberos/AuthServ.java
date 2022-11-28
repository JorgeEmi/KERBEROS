/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerberos;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

/**
 *
 * @author jorge
 */
public class AuthServ {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws NoSuchAlgorithmException {

        int PORT = 5000;
         //  Autoridadcertificadora info
        int AUTH_PORT = 5000;
        
    try {
            Encoder encoder = new Encoder();
            Scanner scanner = new Scanner(System.in);
            

            //  Autoridad
            System.out.println(" ¬ Ingresa la IP de la autoridad certificadora: ");
            InetAddress ipC = InetAddress.getByName(scanner.nextLine());

            //  Clave Privada Cliente
            Socket socketPrivateKeyEmisor = new Socket(ipC, AUTH_PORT);
            PrivateKey privateKeyEmisor = encoder.privateKey(socketPrivateKeyEmisor);

            System.out.println(" └ Claves recibidas");
        }catch (IOException ex) {
        }

        
    }
    
}
