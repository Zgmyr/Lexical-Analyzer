import java.io.FileReader;

public class FileReaderTest {
    public static void main(String[] args) {

        /* playing with java.io.FileReader */
        try {
            // input should be read form a file "data_file.txt"
            FileReader in_fp = new FileReader("data_file.txt");
            int i;
            StringBuilder lexeme = new StringBuilder();

            // reads through each character one at a time
            while ((i = in_fp.read()) != -1) {
                System.out.println((char)i);
                lexeme.append((char) i);
            }

            System.out.println("lexeme = " + lexeme.toString());
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
