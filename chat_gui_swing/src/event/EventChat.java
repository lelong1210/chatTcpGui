package event;

import core.FileInfo;

public interface EventChat {

    public void sendMessage(String text);
    public void newMessage(String text);
    public void sendFile();
    public void downloadFile(FileInfo fileInfo);
}
