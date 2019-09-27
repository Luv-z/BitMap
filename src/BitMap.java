import java.util.ArrayList;
import java.util.Scanner;

public class BitMap {
    private final int MAXW = 8;
    private final int MAXH = 8;

    public int[][] bitMap;
    public int freeChunks = 0;

    public static ArrayList<FileChunks> file = new ArrayList();

    public BitMap() {
        bitMap = new int[MAXW][MAXH];
        for (int i = 0; i < MAXW; i++) {
            for(int j = 0; j < MAXH; j++) {
                bitMap[i][j] = (int)(Math.random()*2);
                if (bitMap[i][j] == 0) {
                    freeChunks++;
                }
            }
        }
    }

    public void Display() {
        System.out.println("当前位示图：");
        for (int i = 0; i < MAXW; i++) {
            for(int j = 0; j < MAXH; j++) {
                System.out.print(bitMap[i][j] + " ");
                if(j == MAXH - 1) {
                    System.out.println();
                }
            }
        }
    }

    public void DisplayFile() {
        System.out.println("当前文件：");
        for (int i = 0; i < file.size(); i++) {
            System.out.println(file.get(i).fileName);
            FileChunks temp = file.get(i).next;
            while (temp != null) {
                System.out.println(file.get(i).fileName + "(" + temp.byteNum + "," + temp.bitNum + ") ");
                temp = temp.next;
            }
        }
    }

    public void Distribute() {
        Scanner user = new Scanner(System.in);
        System.out.print("请输入文件名：");
        String fileName = user.next();
        FileChunks newFile = new FileChunks(fileName);
        FileChunks temp = newFile;
        System.out.print("请输入该文件需要的块数：");
        int need = user.nextInt();
        System.out.println("所申请的块对应的物理地址：");
        if (need <= freeChunks) {
            for (int i = 0; i < MAXW; i++) {
                for(int j = 0; j < MAXH; j++) {
                    if (bitMap[i][j] == 0) {
                        bitMap[i][j] = 1;
                        FileChunks newChunk = new FileChunks(i,j);
                        temp.next = newChunk;
                        temp = newChunk;
                        System.out.println("柱面" + i + " 磁道" + (j / 4) + " 物理记录" + (j % 4));
                        freeChunks--;
                        need--;
                        if (need == 0) {
                            break;
                        }
                    }
                }
                if (need == 0) {
                    break;
                }
            }
            file.add(newFile);
        }
        else {
            System.out.println("空闲块数量不足！");
        }
    }

    public void Release(int cNum, int mNum, int pNum) {
        int i = cNum;
        int j = mNum * 4 + pNum;
        System.out.println("释放块字节号：" + i + " 位数：" + j);
        if (bitMap[i][j] != 0) {
            bitMap[i][j] = 0;
            freeChunks++;
        } else {
            System.out.println("该块未被占用！");
        }
    }

    public void ReleaseFile() {
        Scanner user = new Scanner(System.in);
        System.out.print("请输入要释放的文件名：");
        String fileName = user.next();
        int fileNum = 0;
        boolean findFile = false;
        for (int i = 0; i < file.size(); i++) {
            if (fileName.equals(file.get(i).fileName)) {
                fileNum = i;
                findFile = true;
                FileChunks temp = file.get(fileNum).next;
                while (temp != null) {
                    Release(temp.cNum, temp.mNum, temp.pNum);
                    temp = temp.next;
                }
                file.remove(fileNum);
                break;
            }
        }
        if (!findFile) {
            System.out.println("未找到该文件！");
        }
    }

}
