package com.example.a4;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HexFormat;

public class Counter implements Task {

    public void execute() {
        System.out.println("\u25BD\u25BD----------Counter start----▽▽");
        while (true) {
            String str = getSha256();
            if (str.startsWith("dddd")) {
                System.out.println("------------" + str);
                break;
            }
        }
        System.out.println("\u25B2\u25B2----------Counter end----▲▲");
    }

    private String getSha256() {
        String ret = null;
        try {
            SecureRandom random = new SecureRandom();
            byte[] iv = new byte[16];
            random.nextBytes(iv);
            MessageDigest sha256 = MessageDigest.getInstance("SHA-512");
            byte[] sha256Byte = sha256.digest(iv);
            HexFormat hex = HexFormat.of();
            ret = hex.formatHex(sha256Byte);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ret;
    }
    
    @Override
    public synchronized void waitThread() throws InterruptedException {
        wait();
    }

    @Override
    public synchronized void notifyThread() {
        notify();
    }
}
