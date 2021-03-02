package server;

import java.sql.SQLException;

public class StartServer {
    private static final Object mon = new Object();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ThreeThreads();

        new Server();
    }

    // task 3.4 - 1)
    public static void ThreeThreads(){
        new Thread(() -> { // t1
            synchronized (mon) {
                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("A");
                        mon.wait();
                        mon.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> { // t2
            synchronized (mon) {
                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("B");
                        mon.wait();
                        mon.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> { // t3
            synchronized (mon) {
                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("C");
                        mon.notify();
                        mon.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
