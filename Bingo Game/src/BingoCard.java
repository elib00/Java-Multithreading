import java.util.ArrayList;
import java.util.Random;

public class BingoCard {
    int[][] numbers;
    int id;

    public BingoCard(int id) {
        this.id = id;
        numbers = new int[5][5];
        Random rand = new Random();

        int[][] ranges = { { 1, 15 }, { 16, 30 }, { 31, 45 }, { 46, 60 }, { 61, 75 } };
        for (int i = 0; i < 5; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                int max = ranges[i][1];
                int min = ranges[i][0];
                int randomNumber;
                while (true) {
                    randomNumber = rand.nextInt(max - min + 1) + min;
                    if (!temp.contains(randomNumber)) {
                        temp.add(randomNumber);
                        break;
                    }
                }
                numbers[j][i] = randomNumber;
            }
        }

        // set the middle to zero
        numbers[2][2] = 0;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                sb.append(numbers[row][col]).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
