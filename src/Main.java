import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException {
        MyEncoder myEncoder = new MyEncoder();
        MyDecoder myDecoder = new MyDecoder();

        myEncoder.encode();
        myDecoder.decode();

//        File file1 = new File("C:\\Users\\micha\\Desktop\\Projekty\\4sem\\kkd2\\src\\raw.txt");
//        byte[] fileContent = Files.readAllBytes(file1.toPath());
//
//        File file2 = new File("C:\\Users\\micha\\Desktop\\Projekty\\4sem\\kkd2\\src\\decoded.txt");

//        byte b1 = (byte) 'w';
//        String s1 = String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0');
//        System.out.println(s1);

//        OutputStream os = new FileOutputStream(file2);
//        StringBuilder codebuilder = new StringBuilder();
//
//        for(byte b: fileContent){
//            String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
//            System.out.println("bin: " + s1 + " , dec " + Integer.parseInt(s1, 2));
//            codebuilder.append(s1);
////            os.write(Byte.parseByte(s1, 2));
//            System.out.println("char: " + (char)Integer.parseInt(s1, 2));
//        }

//        ByteBuffer bytes = ByteBuffer.allocate(codebuilder.length()/8).put(Byte.parseByte(String.valueOf(codebuilder), 2));

//        byte[] array = bytes.array();


//        os.write(array);
//        os.close();
//
//        System.out.println(String.valueOf(15));
//        String string = "012345678";
//        System.out.println(string.substring(0, 8));

//        String bin = "10000000";
//        System.out.println("len: " + bin.length());
//        System.out.println("bin: " + bin + " dec: " + Integer.parseInt(bin, 2));
//        System.out.println(Byte.parseByte(bin, 2));

//        byte b = -4;
//        byte c = 4;
//        byte d = 127;
//        byte e = 128;
//
//
//        System.out.println(Integer.toBinaryString(b));
//        System.out.println(b);
//        System.out.println(Integer.toBinaryString(c));
    }
}
