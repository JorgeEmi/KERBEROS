/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerberos;

import java.io.IOException;
import java.net.ServerSocket;
import java.security.NoSuchAlgorithmException;
import javax.crypto.SecretKey;

/**
 *
 * @author jorge
 */
public class AutoCert {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        int PORT = 5000;

        // Llaves Cliente
        KeyGenerator clienteKeys = new KeyGenerator();
        
        //Llaves TGS
        KeyGenerator tgsKeys = new KeyGenerator();

        //Llaves Servidor
        KeyGenerator servidorKeys = new KeyGenerator();     
        
        //Clave secreta entre cliente y tgs-AS,TGS
        SecretKey secretKeyCTGS = new KeyManager().generateSecretKey();
        
        //Clave secreta entre cliente y servidor-TGS,
        SecretKey secretKeyCV = new KeyManager().generateSecretKey();

        try {
            KeyDelivery kd = new KeyDelivery();

            //Cliente
            ServerSocket ssClientePriv = new ServerSocket(PORT);//llave privada del cliente
            System.out.println("Esperanding al Cliente...");
            kd.sendPrivateKey(clienteKeys.getPrivateKey(), ssClientePriv);
            System.out.println("Claves Enviadas al Cliente");         
            
            
            //AS
            ServerSocket ssClientePubl = new ServerSocket(PORT);//llave privada de la AS
            System.out.println("Esperanding al AS...");
            kd.sendPublicKey(clienteKeys.getPublicKey(), ssClientePubl);

            
            ServerSocket ssTGSPubl = new ServerSocket(PORT);//llave publica del tgs
            kd.sendPublicKey(tgsKeys.getPublicKey(), ssTGSPubl);
          
            
            ServerSocket sssecretkeyctgs = new ServerSocket(PORT);//secretkey del cliente y el tgs
            kd.sendSecretKey(secretKeyCTGS, sssecretkeyctgs);
            System.out.println("Claves Enviadas al AS");
            
            
            //TGS
            ServerSocket ssTGSPriv = new ServerSocket(PORT);//llave privada del tgs
            System.out.println("Esperanding al TGS...");
            kd.sendPrivateKey(tgsKeys.getPrivateKey(), ssTGSPriv);

            
            ServerSocket ssServidorPubl = new ServerSocket(PORT);//llave publica del servidor
            kd.sendPublicKey(servidorKeys.getPublicKey(), ssServidorPubl);

            
            ServerSocket sssecretkeycv = new ServerSocket(PORT);//secretkey del cliente y el tgs
            kd.sendSecretKey(secretKeyCV, sssecretkeycv);
            System.out.println("Claves Enviadas al TGS");

            
            
            //Servidor
            ServerSocket ssServidorPriv = new ServerSocket(PORT);//llave secreta del sevidor
            System.out.println("Esperanding al Servidor...");
            kd.sendPrivateKey(servidorKeys.getPrivateKey(), ssServidorPriv);
            System.out.println("Claves Enviadas al Servidor");

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
