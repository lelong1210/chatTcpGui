package event;

public class PublicEvent {

    private static PublicEvent instance;
    private EventChat eventChat;
    private EventLogin eventLogin;
    private EventExitOrLogout eventExitOrLogout;
    private EventServer eventServer;

    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    private PublicEvent() {

    }

    public void addEventChat(EventChat event) {
        this.eventChat = event;
    }

    public void addEventLogin(EventLogin event) {
        this.eventLogin = event;
    }
    
    public void addeventExitOrLogout(EventExitOrLogout event) {
        this.eventExitOrLogout = event;
    }
    public void addeventServer(EventServer event) {
        this.eventServer = event;
    }

    public EventChat getEventChat() {
        return eventChat;
    }

    public EventLogin getEventLogin() {
        return eventLogin;
    }
    
    public EventExitOrLogout geteventExitOrLogout() {
        return eventExitOrLogout;
    }
    
    public EventServer getEventServer() {
        return eventServer;
    }
}
