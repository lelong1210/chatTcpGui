package event;

public interface EventLogin {

    public void login(String username, String password);

    public void register(String username, String password, String rePassword);
}
