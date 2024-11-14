import java.util.Random;
public class Test {
    public static void main(String[] args) {
        String name = "Jay";
        String password = getPassword();
    }

    public static String getPassword() {
        String password = "";
        for (int i = 0; i < 8; i++)
            password += getRandomChar();
        System.out.println("");
        return password;
    }

    public static char getRandomChar() {
        Random random = new Random();
        return (char)(random.nextInt((122 - 32) + 1) + 32);
    }
}

