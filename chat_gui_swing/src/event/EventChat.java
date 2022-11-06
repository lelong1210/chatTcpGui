package event;

public interface EventChat {

    public void sendMessage(String text);
    public void receiveMessage(String text);
    public void newMessage(String text);
    public void sendFile();
}
