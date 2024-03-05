import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BingoGame implements Runnable {
    List<BingoCard> cards;
    static boolean[] result;
    static boolean isBingo;

    public BingoGame() {
        isBingo = false;
        result = new boolean[76];
        result[0] = true;

        for (int i = 1; i < 76; i++) {
            result[i] = false;
        }
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many players? ");
        int cnt = sc.nextInt();
        sc.close();

        cards = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            cards.add(new BingoCard(i + 1));
        }
        for (BingoCard card : cards) {
            System.out.println("Card " + card.id);
            System.out.println(card);
        }

        // Thread[] rowThreads = new Thread[cnt];
        // for (int i = 0; i < cnt; i++) {
        // for (int j = 0; j < 5; j++) {
        // rowThreads[i] = new Thread(new BingoRowChecker(cards.get(i), j + 1));
        // rowThreads[i].start();
        // }
        // }

        // Thread[] colThreads = new Thread[cnt];
        // for (int i = 0; i < cnt; i++) {
        // for (int j = 0; j < 5; j++) {
        // colThreads[i] = new Thread(new BingoColumnChecker(cards.get(i), j + 1));
        // colThreads[i].start();
        // }
        // }

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < 5; j++) {
                threads.add(new Thread(new BingoColumnChecker(cards.get(i), j + 1)));
                threads.add(new Thread(new BingoRowChecker(cards.get(i), j + 1)));
            }
        }

        for (Thread t : threads) {
            t.start();
        }

        Random rand = new Random();

        while (!isBingo) {
            int randomNumber;
            while (true) {
                randomNumber = rand.nextInt(75) + 1;
                if (!result[randomNumber]) {
                    result[randomNumber] = true;
                    break;
                }
            }

            System.out.println("Number :" + randomNumber);

            synchronized (result) {
                result.notifyAll();
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.print("Chosen numbers so far: ");
            for (int i = 1; i < 76; i++) {
                if (result[i]) {
                    System.out.print(i + " ");
                }
            }

            System.out.println();
        }

        // TODO randomly get number from 1-75 while not bingo
    }
}
