import javax.swing.*;
import java.util.List;

public class ReminderThread extends Thread {
    private List<event> events;

    public ReminderThread(List<event> events) {
        this.events = events;
    }

    public void run() {
        while (true) {
            for (event e : events) {
                if (e.isDueSoon()) {
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(null, "Upcoming Event: " +
                            e.showTitle() +
                            "\nAt: " + e.getDateTime() +
                            "\nDescription: " + e.showDescription())
                    );
                    e.setEventStatus(true); // Mark notified to avoid repeat popups
                }
            }
            try {
                Thread.sleep(30 * 1000); // 30 seconds
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
