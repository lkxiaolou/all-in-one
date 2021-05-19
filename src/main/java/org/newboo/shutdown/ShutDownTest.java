package org.newboo.shutdown;

public class ShutDownTest {

    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("I'm shutdown hook...");
            }
        });

        System.out.println("main running");

        while (true) {
            System.out.println("main running");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
