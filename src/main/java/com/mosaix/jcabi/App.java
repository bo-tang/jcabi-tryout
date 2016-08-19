package com.mosaix.jcabi;

import com.jcabi.ssh.Shell;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.jcabi.ssh.SSH;

/**
 * Hello world!
 *
 */
public class App {
    final private static String private_key =
            readFile(System.getProperty("user.home") + "/.ssh/id_rsa",
                    StandardCharsets.UTF_8);

    private static String readFile(String path, Charset encoding) {
        byte[] encoded = null;
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new String(encoded, encoding);
    }

    public static void main(String[] args) {
        String stdout = null;
        Shell shell = null;
        try {
            shell = new SSH("192.168.122.8", 22, "mosaix", private_key);
            stdout = new Shell.Plain(shell).exec("echo 'Hello, world!'");
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(stdout);

    }
}
