import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class executer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        reminderManager rm = new reminderManager();

        // Start reminder thread
        ReminderThread t = new ReminderThread(rm.arr);
        t.start();

        while (true) {
            System.out.println("**** Event Menu ****");
            System.out.println("1. Add event");
            System.out.println("2. Display events");
            System.out.println("3. Show occured events");
            System.out.println("4. Remove Event");
            System.out.println("5. Exit");

            int ch = 0;
            while (true) {
                try {
                    System.out.print("Enter your choice: ");
                    ch = Integer.parseInt(sc.nextLine());
                    if (ch < 1 || ch > 5) {
                        System.out.println("Please enter a number from 1 to 5.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("That isn't a valid number.");
                }
            }

            switch (ch) {
                case 1: {
                    String ti, d, dateStr, timeStr;
                    while (true) {
                        System.out.print("Enter title: ");
                        ti = sc.nextLine().trim();
                        if (!ti.isEmpty()) break;
                        System.out.println("Title can't be empty.");
                    }
                    while (true) {
                        System.out.print("Enter description: ");
                        d = sc.nextLine().trim();
                        if (!d.isEmpty()) break;
                        System.out.println("Description can't be empty.");
                    }
                    LocalDateTime dt;
                    while (true) {
                        System.out.print("Enter date (yyyy-MM-dd): ");
                        dateStr = sc.nextLine().trim();
                        System.out.print("Enter time (HH:mm): ");
                        timeStr = sc.nextLine().trim();
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            dt = LocalDateTime.parse(dateStr + " " + timeStr, formatter);
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date or time format. Please use yyyy-MM-dd for date and HH:mm for time.");
                        }
                    }
                    rm.addEvent(new event(ti, d, dt));
                    break;
                }
                case 2:
                    rm.viewEvents();
                    break;
                case 3:
                    rm.showEventAlreadyHappened();
                    break;
                case 4:
                    rm.viewEvents();
                    if (!rm.arr.isEmpty()) {
                        int ind;
                        while (true) {
                            System.out.print("Enter event number to remove: ");
                            String input = sc.nextLine();
                            try {
                                ind = Integer.parseInt(input);
                                rm.removeEvent(ind);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a valid integer.");
                            }
                        }
                    }
                    break;
                case 5:
                    System.out.println("Exited Event Reminder System.");
                    sc.close();
                    return;
                default:
                    System.out.println("Not a valid input!");
            }
        }
    }
}

