package org.newboo.shutdown;

import sun.misc.Signal;
import sun.misc.SignalHandler;

public class SignalHandler2Test  {

    public static void main(String[] args) {
        while (true) {
            System.out.println("main running");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    @Override
//    public void handle(Signal signal) {
//        System.out.println("receive signal " + signal.getName() + "-" + signal.getNumber());
//        System.exit(0);
//    }
}
