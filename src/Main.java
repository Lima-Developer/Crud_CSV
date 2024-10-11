import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("tecnologia.csv", "r")) {
            String line;
            while ((line = raf.readLine()) != null) {
                String[] values = line.split(",");
                for (String value : values) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
