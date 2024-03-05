import java.util.ArrayList;
import java.util.List;

public abstract class BingoPattern implements Runnable {
    List<BingoChecker> checkers;
    List<Thread> threads;
    BingoCard card;

    public BingoPattern(BingoCard card) {
        checkers = new ArrayList<>();
        this.card = card;
        for (int i = 0; i < 5; i++) {
            checkers.add(new BingoRowChecker(card, i));
            checkers.add(new BingoColumnChecker(card, i));
        }
        //
        // for(BingoChecker checker : checkers){
        // threads.add(checker);
        // }

    }

    @Override
    public void run() {

    }
}