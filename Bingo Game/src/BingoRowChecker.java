
public class BingoRowChecker extends BingoChecker {
    int row;

    public BingoRowChecker(BingoCard card, int row) {
        super(card);
        this.row = row - 1;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int cardElement = card.numbers[row][i];
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