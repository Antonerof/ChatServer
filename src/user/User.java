
package user;

public class User {
    private String login;
    private String password;
    private byte status;//0 - offline, 1 - online
    private long lastActivity;
    private int idRoom;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        status = 1;
        lastActivity = System.currentTimeMillis();
        idRoom = 0;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public long getLastActivity() {
        return lastActivity;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public void setLastActivity(long lastActivity) {
        this.lastActivity = lastActivity;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }
}
