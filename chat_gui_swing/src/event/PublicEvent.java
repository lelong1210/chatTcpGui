package event;

public class PublicEvent {

    private static PublicEvent instance;
    private EventChat eventChat;
    private EventLogin eventLogin;
    private EventExitOrLogout eventExitOrLogout;

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


    public EventChat getEventChat() {
        return eventChat;
    }

    public EventLogin getEventLogin() {
        return eventLogin;
    }
    
    public EventExitOrLogout geteventExitOrLogout() {
        return eventExitOrLogout;
    }
}
