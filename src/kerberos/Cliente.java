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
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author jorge
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, InterruptedException {
        //  información del cliente
        String IDCliente = "Cliente";

        //  Autoridadcertificadora info
        int AUTH_PORT = 5000;

        //  TGS info
        int Port_TGS = 1202;

        //  Server info
        int CLIENT_SERVER_PORT = 5001;

        //  Variables to use later on.
        String receiverName;
        SecretKey ClientAS;
        SecretKey sessionKeyClientTGS;
        SecretKey sessionKeyClientServer;

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

