import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventUI extends JFrame {
    private JTextField titleField, dateField, timeField;
    private JTextArea descField;
    private JButton addButton;
    private List<event> eventList = new ArrayList<>();
    private ReminderThread reminderThread;

    public EventUI() {
        setTitle("Event Reminder");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        titleField = new JTextField();
        dateField = new JTextField();
        timeField = new JTextField();
        descField = new JTextArea(3, 20);
        addButton = new JButton("Add Event");

        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Date (yyyy-MM-dd):"));
        add(dateField);
        add(new JLabel("Time (HH:mm):"));
        add(timeField);
        add(new JLabel("Description:"));
        add(descField);
        add(addButton);

        addButton.addActionListener(e -> {
            String title = titleField.getText();
            String dateStr = dateField.getText();
            String timeStr = timeField.getText();
            String desc = descField.getText();
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(dateStr + " " + timeStr, dtf);
                event ev = new event(title, desc, dateTime);
                eventList.add(ev);
                EventFileWriter.saveEvents(eventList);
                JOptionPane.showMessageDialog(this, "Event Added: " + title);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid date/time format. Date: yyyy-MM-dd, Time: HH:mm (e.g. 14:30)");
            }
        });

        setVisible(true);

        // Start reminder thread when UI opens
        reminderThread = new ReminderThread(eventList);
        reminderThread.start();
    }

    public static void main(String[] args) {
        new EventUI();
    }
}
