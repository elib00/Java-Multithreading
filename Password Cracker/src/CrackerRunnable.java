public class CrackerRunnable implements Runnable{
    char ch;
    String pass;

    static boolean cracked = false;

    public CrackerRunnable(char ch, String pass){
        this.ch = ch;
        this.pass = pass;
    }
    @Override
    public void run() {
        int len = pass.length();
        String attack = Character.toString(ch) + "a".repeat(len - 1);
        while(!cracked){
            System.out.println("Still cracking: " + attack);
            int i;
            for(i = len - 1; attack.charAt(i) == 'z'; i--);
            String first = attack.substring(0, i);
            char next = (char) (attack.charAt(i) + 1);
            String after = "a".repeat(len - i - 1);
            attack = first + next + after;
            if(attack.equalsIgnoreCase(pass)){
                System.out.println("The password is: " + attack);
                cracked = true;
            }
        }
    }
}
