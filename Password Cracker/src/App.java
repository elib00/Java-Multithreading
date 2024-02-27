import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a password: ");
        String pass = sc.nextLine();

        Thread[] threads = new Thread[26];

        for(int i = 0; i < 26; i++){
            threads[i] = new Thread(new CrackerRunnable((char) ('a' + i), pass));
        }

        for(int i = 0; i < 26; i++){
            threads[i].start();
        }

        sc.close();
    }
}
