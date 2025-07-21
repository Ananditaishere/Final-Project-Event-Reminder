import java.util.ArrayList;

public class reminderManager {
    ArrayList<event> arr;

    public reminderManager() {
        arr = new ArrayList<>();
    }

    public void addEvent(event e) {
        arr.add(e);
        System.out.println("Event added to the list of events!");
    }

    public void viewEvents() {
        if (arr.isEmpty()) {
            System.out.println("There is no event to be listed!");
            return;
        }
        for (int i = 0; i < arr.size(); i++) {
            System.out.println((i + 1) + ". " + arr.get(i).showTitle());
            System.out.println("Description: " + arr.get(i).showDescription());
            System.out.println("Date & Time: " + arr.get(i).getDateTime());
            System.out.println("Status: " + (arr.get(i).getEventStatus() ? "Completed" : "Pending"));
        }
    }

    public void showEventAlreadyHappened() {
        if (arr.isEmpty()) {
            System.out.println("No event in the list");
            return;
        }
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getEventStatus()) {
                System.out.println((i + 1) + ". " + arr.get(i).showTitle());
                System.out.println(arr.get(i).showDescription());
                System.out.println("Date & Time: " + arr.get(i).getDateTime());
            }
        }
    }

    public void updateEventStatus(int ind) {
        if (ind <= 0 || ind > arr.size() || arr.isEmpty()) return;
        arr.get(ind - 1).setEventStatus(true);
        System.out.println("Event marked as completed!");
    }

    public void removeEvent(int ind) {
        if (ind <= 0 || ind > arr.size() || arr.isEmpty()) {
            System.out.println("Not able to remove the event");
            return;
        }
        arr.remove(ind - 1);
        System.out.println("Event removed.");
    }
}
