/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cojoytuerto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author akiel
 */
public class TAuth {

    public TAuth() throws IOException, TwitterException { //Constructor de la clase
        File file = new File("auth_file.txt");
        if (!file.exists()) {

            ConfigurationBuilder configBuilder = new ConfigurationBuilder();
            configBuilder.setDebugEnabled(true)
                    .setOAuthConsumerKey("wCWSOW8xHlxfQq24kSxXuXNm9")
                    .setOAuthConsumerSecret("5B1R4bxZv7TcO7Vmq3NvhM3Bo3YcO0qCIJP2vDD9HnOaPL63YD");
            configBuilder.setHttpProxyHost("127.0.0.1");
            configBuilder.setUseSSL(true);
            configBuilder.setHttpProxyPort(3128);
            Twitter OAuthTwitter = new TwitterFactory(configBuilder.build()).getInstance();
            RequestToken requestToken = null;
            AccessToken accessToken = null;
            String url = null;

            do {
                try {
                    requestToken = OAuthTwitter.getOAuthRequestToken();
                    System.out.println("Request Token obtenido con éxito.");
                    System.out.println("Request Token: " + requestToken.getToken());
                    System.out.println("Request Token secret: " + requestToken.getTokenSecret());
                    url = requestToken.getAuthorizationURL();
                    url = url.replace("http://", "https://");
                    System.out.println("URL:");
                    System.out.println(url);
                } catch (TwitterException ex) {
                    Logger.getLogger(TAuth.class.getName()).log(Level.SEVERE, null, ex);
                }
                BufferedReader lectorTeclado = new BufferedReader(new InputStreamReader(System.in));
//Abro el navegador. Firefox, en este caso.
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec("firefox " + url);
                } catch (Exception e) {
                }
//Nos avisa de que introduciremos el PIN a continuación
                
                System.out.print("Introduce el PIN del navegador y pulsa intro.nn PIN: ");
//Leemos el PIN
//                String pin = lectorTeclado.readLine();
                String pin = JOptionPane.showInputDialog(null, "Introduce el PIN del navegador y pulsa intro.nn PIN: ");
                if (pin.length() > 0) {
                    accessToken = OAuthTwitter.getOAuthAccessToken(requestToken, pin);
                } else {
                    accessToken = OAuthTwitter.getOAuthAccessToken(requestToken);
                }
            } while (accessToken == null);

            System.out.println("nnAccess Tokens obtenidos con éxito.");
            System.out.println("Access Token: " + accessToken.getToken());
            System.out.println("Access Token secret: " + accessToken.getTokenSecret());
            FileOutputStream fileOS = null;
//            File file;
            String content = accessToken.getToken() + "\n" + accessToken.getTokenSecret();
            try {
//                file = new File("auth_file.txt");
                fileOS = new FileOutputStream(file);
//Si el archivo no existe, se crea
                if (!file.exists()) {
                    file.createNewFile();
                }
//Se obtiene el contenido en Bytes
                byte[] contentInBytes = content.getBytes();
                fileOS.write(contentInBytes);
                fileOS.flush();
                fileOS.close();
                System.out.println("Escritura realizada con éxito.");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOS != null) {
                        fileOS.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
