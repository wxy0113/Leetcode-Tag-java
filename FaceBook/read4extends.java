import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class readn4extends {
    public static void main(String[] args) {
        int buffSize = 5;
        readNWrite reader = new readNWrite(buffSize);
        char[] buf = new char[buffSize];

        System.out.println(reader.write("abc"));

        System.out.println(reader.write("def"));

        int len = reader.read(buf, 3);
        for (int i = 0; i < len; i ++)
            System.out.print(buf[i] + " ");
        System.out.println();

        System.out.println(reader.write("xyzabc"));

        len = reader.read(buf, 8);
        for (int i = 0; i < len; i ++)
            System.out.print(buf[i] + " ");
        System.out.println();
    }
}
class readOne extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] temp = new char[4];
        int index = 0;
        while (true) {
            int size = read4(temp);
            for (int i = 0; i < size && index < n; i++) {
                buf[index++] = temp[i];
            }
            if (size == 0 || index == n) return index;
        }
    }
}
class readN extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    int head = 0, tail = 0;
    char[] buffer = new char[4];
    public int read(char[] buf, int n) {
        int i = 0;
        while (i < n) {
            if (head == tail) {
                head = 0;
                tail = read4(buffer);
                if (head == tail) break;
            }
            while (i < n && head < tail) {
                buf[i++] = buffer[head++];
            }
        }
        return i;
    }

}
class readNWrite {
    char[] buffer;
    int left, right;
    public readNWrite(int n) {
        buffer = new char[n+1];
        left = 0;
        right = 0;
    }
    public int read(char[] buf, int n) {
        int count = 0;
        while (count < n && left != right) {
            left = (left+1)%buffer.length;
            buf[count++] = buffer[left];
        }
        return count;
    }
    public int write(String s) {
        int size = s.length();
        int count = 0;
        while (count < size && (right+1)%buffer.length != left) {
            right = (right+1)%buffer.length;
            buffer[right] = s.charAt(count++);
        }
        return count;
    }
}