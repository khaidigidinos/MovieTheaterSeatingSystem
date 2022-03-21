import java.io.*;

public class Driver {
    public static void main(String... args) {
        try {
            FileReader file = new FileReader(args[0]);
            BufferedReader reader = new BufferedReader(file);
            String line;
            MovieTheaterSeating service = new MovieTheaterSeating();
            while((line = reader.readLine()) != null) {
                service.bookSeatAndStoreResult(line);
            }
            service.printResult();
            service.printInformation();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
