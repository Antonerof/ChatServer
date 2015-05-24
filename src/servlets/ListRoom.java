package servlets;

import room.Room;
import room.RoomList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ListRoom extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OutputStream outputStream = resp.getOutputStream();
        sendRoomsList(outputStream);
    }

    private void sendRoomsList(OutputStream outputStream) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        for (Room room : RoomList.getListRooms()) {
            writer.write(room.getIdRoom() + " " + room.getNameOfRoom());
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }
}
