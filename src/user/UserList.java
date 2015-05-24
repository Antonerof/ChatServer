package user;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static UserList ourInstance = new UserList();

    private static List<User> list = new ArrayList<User>();

    public static UserList getInstance() {
        return ourInstance;
    }

    private UserList() {
    }

    public synchronized void addUser(User user){
        list.add(user);
    }

    public synchronized int isExistsUser(String login){
        for (User userInList : list) {
            if (userInList.getLogin().equals(login)){
                return list.indexOf(userInList);
            }
        }
        return -1;
    }

    public synchronized boolean isCorrectPassword(int userPositionInList, String password){
        if (list.get(userPositionInList).getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public synchronized void writeLastloginTime(long time, String name){
        for (User user : list) {
            if (user.getLogin().equals(name)){
                user.setLastActivity(time);
                break;
            }
        }
    }

    public synchronized void checkUsersStatus(long timeout){
        long currentTime = System.currentTimeMillis();
        for (User user : list) {
            if ((currentTime - user.getLastActivity()) > timeout){
                user.setStatus((byte) 0);
            }
        }
    }

    public synchronized void setUserStatus(int position, byte status){
        list.get(position).setStatus((status));// set online
    }

    public static List<User> getList() {
        return list;
    }

    public static synchronized User getUserByName(String name){
        for (User user1 : list) {
            if (user1.getLogin().equals(name)){
                return user1;
            }
        }
        return null;
    }
}
