package com.ejst1psp;

public class Ej2 {
    public static void main(String [] args) {
        ProcessBuilder pb = new ProcessBuilder("notepad");

        try {
            ProcessHandle ph = ProcessHandle.current();
            pb.start();
            ProcessHandle.Info info = ph.info();

            System.out.println(ph.pid());
            System.out.println(info.arguments());
            System.out.println(info.command());
            System.out.println(info.startInstant());
            System.out.println(info.totalCpuDuration());
            System.out.println(info.user());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
