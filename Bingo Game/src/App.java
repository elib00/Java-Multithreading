public class App {
    public static void main(String[] args) {
        Thread game = new Thread(new BingoGame());
        game.start();
    }
}
