package org.newboo.shutdown;

import sun.misc.Signal;
import sun.misc.SignalHandler;

public class SignalHandlerTest implements SignalHandler {

    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("I'm shutdown hook ");
            }
        });

        SignalHandler sh = new SignalHandlerTest();
        Signal.handle(new Signal("HUP"), sh);
        Signal.handle(new Signal("INT"), sh);
        //Signal.handle(new Signal("QUIT"), sh);// 该信号不能捕获
        Signal.handle(new Signal("ABRT"), sh);
        //Signal.handle(new Signal("KILL"), sh);// 该信号不能捕获
        Signal.handle(new Signal("ALRM"), sh);
        Signal.handle(new Signal("TERM"), sh);

        while (true) {
            System.out.println("main running");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handle(Signal signal) {
        System.out.println("receive signal " + signal.getName() + "-" + signal.getNumber());
        System.exit(0);
    }
}
