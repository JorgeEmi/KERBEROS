package kerberos;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Encoder {

    public Encoder() {}
    
    public PublicKey publicKey(Socket socketPublicK) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] lenb = new byte[4];
        socketPublicK.getInputStream().read(lenb, 0, 4);
        ByteBuffer bb = ByteBuffer.wrap(lenb);
        int len = bb.getInt();
        byte[] publicKeyBytes = new byte[len];
        socketPublicK.getInputStream().read(publicKeyBytes);
        X509EncodedKeySpec ks = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicReceptorKey = kf.generatePublic(ks);
        socketPublicK.close();

        return publicReceptorKey;
    }

    public PrivateKey privateKey(Socket socketPrivateK) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        
        byte[] lenb = new byte[4];
        socketPrivateK.getInputStream().read(lenb, 0, 4);
        ByteBuffer bb = ByteBuffer.wrap(lenb);
        int len = bb.getInt();
        byte[] privateKeyBytes = new byte[len];
        socketPrivateK.getInputStream().read(privateKeyBytes);
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = kf.generatePrivate(ks);
        socketPrivateK.close();
        
        return privateKey;
    }
    public SecretKey secretKey(Socket socketSecretK) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        
        byte[] lenb = new byte[4];
        socketSecretK.getInputStream().read(lenb, 0, 4);
        ByteBuffer bb = ByteBuffer.wrap(lenb);
        int len = bb.getInt();
        byte[] privateKeyBytes = new byte[len];
        socketSecretK.getInputStream().read(privateKeyBytes);
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory kf = KeyFactory.getInstance("AES");
        PrivateKey privateKey = kf.generatePrivate(ks);
        socketSecretK.close();
        
        return privateKey;
    }

}
