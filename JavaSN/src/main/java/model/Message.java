package model;

public class Message {
    private Integer id;

    private String userFromName;
    private String userToName;

    private String text;
    private Long date;

    public Message(Integer id, String from, String to, String text, Long date) {
        this.id = id;
        this.text = text;
        this.userFromName = from;
        this.userToName = to;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public Long getDate() {
        return date;
    }

    public String getUserFromName() {
        return userFromName;
    }

    public String getUserToName() {
        return userToName;
    }

    @Override
    public String toString() {
        return id + ") " + getUserFromName() + ": " + date + "\n" +
                "\"" + getText() + "\"" + "\n";
    }
}