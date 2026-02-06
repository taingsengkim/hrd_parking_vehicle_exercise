package thread;
class MyThread implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello, thread 1");
    }
}
class YourThread extends Thread{
    @Override
    public void run() {
        System.out.println("Hello, thread 2");
    }
}
public class MultiThread {
    static void main() {

        Thread thread1 = new Thread(()->{
            System.out.println("Running thread1");
        });
        thread1.run();

        MyThread myThread1 = new MyThread();
        myThread1.run();

        new Thread(()->{
            System.out.println("Running Thread 2");
        }).start();

//        MyThread myThread = new MyThread();
//        YourThread yourThread = new YourThread();
//        myThread.run();
//        yourThread.run();
//
//        // Create frame
//        JFrame frame = new JFrame("Concurrent Counters");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new GridLayout(2, 2, 20, 20));
//        frame.setSize(600, 400);
//        frame.setLocationRelativeTo(null);
//
//
//
//        // Counter 1 Value
//        JLabel label1 = new JLabel("0", SwingConstants.CENTER);
//        label1.setFont(new Font("Arial", Font.BOLD, 80));
//        label1.setForeground(Color.RED);
//        label1.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
//        label1.setOpaque(true);
//        label1.setBackground(Color.WHITE);
//
//
//
//        // Counter 2 Value
//        JLabel label2 = new JLabel("0", SwingConstants.CENTER);
//        label2.setFont(new Font("Arial", Font.BOLD, 80));
//        label2.setForeground(Color.BLUE);
//        label2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
//        label2.setOpaque(true);
//        label2.setBackground(Color.WHITE);
//
//        // Add components to frame
//
//        frame.add(label1);
//
//        frame.add(label2);
//        // Show frame
//        frame.setVisible(true);
//        // Thread 1 - Counter 1 (slower - every 200ms)
//        Thread thread1= new Thread(() -> {
//            int counter1 = 0;
//            while (true) {
//                counter1++;
//                final int value = counter1;
//                SwingUtilities.invokeLater(() -> label1.setText(String.valueOf(value)));
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    break;
//                }
//            }
//        });
//        thread1.start();
////        thread1.join();
//
//        // Thread 2 - Counter 2 (faster - every 100ms)
//        Thread thread2= new Thread(() -> {
//            int counter2 = 0;
//            while (true) {
//                counter2++;
//                final int value = counter2;
//                SwingUtilities.invokeLater(() -> label2.setText(String.valueOf(value)));
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    break;
//                }
//            }
//        });
//        thread2.start();

    }
}
