package room;

import user.User;

import java.util.ArrayList;
import java.util.List;

public class Room {
//    private List<message> messageList;
    private List<User> userList;
    private String nameOfRoom;
    private int idRoom;
    private static int countRooms;

    public Room(String nameOfRoom) {
//        this.messageList = new ArrayList<message>();
        this.userList = new ArrayList<User>();
        this.nameOfRoom = nameOfRoom;
        this.idRoom = ++countRooms;
    }

   /* public synchronized void add(message m) {
        messageList.add(m);
    }

    public synchronized List<message> get() {
        List<message> res = new ArrayList<message>();
        res.addAll(messageList);

        return res;
    }*/
    public synchronized void addUser(User user){
        userList.add(user);
    }

    public synchronized void deleteUser(User user){
        userList.remove(user);
    }

    public String getNameOfRoom() {
        return nameOfRoom;
    }

    public int getIdRoom() {
        return idRoom;
    }
}
