public class FileChunks {
    int byteNum;    //字节号
    int bitNum;     //位数
    int cNum;       //柱面号
    int mNum;       //磁道号
    int pNum;       //物理记录号
    String fileName;
    public FileChunks next;

    public FileChunks(String _fileName) {
        fileName = _fileName;
    }

    public FileChunks(int _byteNum, int _bitNum) {
        byteNum = _byteNum;
        bitNum = _bitNum;
        cNum = _byteNum;
        mNum = _bitNum / 4;
        pNum = _bitNum % 4;
    }
}
