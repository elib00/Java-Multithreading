
public class BingoColumnChecker extends BingoChecker {
    int col;

    public BingoColumnChecker(BingoCard card, int col) {
        super(card);
        this.col = col - 1;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int cardElement = card.numbers[i][col];
            while (!BingoGame.result[cardElement]) {
                try {
                    synchronized (BingoGame.result) {
                        BingoGame.result.wait();
                    }
                } catch (InterruptedException e) {

                }
            }
        }

        System.out.println("Card " + card.id + " done: \n" + card);
    }
}
