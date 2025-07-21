import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EventFileWriter {
    public static void saveEvents(List<event> events) {
        try (FileWriter writer = new FileWriter("events.txt", false)) {
            for (event e : events) {
                writer.write(e.getDateTime().toString() + "," + e.showTitle() + "," + e.showDescription() + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
