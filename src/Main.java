import java.io.*;

public class Main {
    public static void funi(int g){
        g = 5;
    }

    public static void main(String[] args) throws IOException {
        String string = "0123456";
        String sub = string.substring(0,6);
        System.out.println(sub + "  " + sub.length());

        int f = 4;
        funi(f);
        System.out.println(f);
        System.out.println((char) 256);
    }
}
