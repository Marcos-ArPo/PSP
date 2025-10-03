package com.ejst1psp;

public class Ej1 {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("notepad");
            Process p  = pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
