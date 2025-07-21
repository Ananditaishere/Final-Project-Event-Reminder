import java.time.LocalDateTime;

public class event {
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private boolean isCompleted;

    public event(String title, String description, LocalDateTime dateTime) {
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.isCompleted = false;
    }

    public String showTitle() { return title; }
    public String showDescription() { return description; }
    public LocalDateTime getDateTime() { return dateTime; }
    public boolean getEventStatus() { return isCompleted; }
    public void setEventStatus(boolean status) { this.isCompleted = status; }

    // Returns true if event is due in the next 5 minutes and not completed
    public boolean isDueSoon() {
        if (isCompleted) return false;
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        return dateTime.isAfter(now) && dateTime.isBefore(now.plusMinutes(5));
    }
}
