import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BitMap bitMap = new BitMap();
        System.out.println("最初的位示图：");
        bitMap.Display();

        boolean b = true;
        int choose = 0;
        Scanner user = new Scanner(System.in);
        while (b) {
            System.out.print("选择功能：");
            choose = user.nextInt();
            if (choose == 0) {
                bitMap.ReleaseFile();
                bitMap.Display();
            }
            else if (choose == 1) {
                bitMap.Distribute();
                bitMap.Display();
                bitMap.DisplayFile();
            }
            else if (choose == -1) {
                break;
            }
        }
    }



}
