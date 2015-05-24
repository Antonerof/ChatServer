package room;

import java.util.ArrayList;
import java.util.List;


public class RoomList {
    private static RoomList ourInstance = new RoomList();
    private static List<Room> list = new ArrayList<Room>();
    static{
        list.add(new Room("test1"));
        System.out.println("Created test room1");
        list.add(new Room("test2"));
        System.out.println("Created test room2");
    }

    public static RoomList getInstance() {
        return ourInstance;
    }

    private RoomList() {
    }


    public static synchronized List<Room> getListRooms() {
        return list;
    }
}
