package servlets;


import user.User;
import user.UserList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class RoomServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream inputStream = req.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String idStr = (reader.readLine());
        int id = Integer.parseInt(idStr);
        String username = reader.readLine();
        List<User> list = UserList.getList();
        for (User user : list) {
            if (user.getLogin().equals(username)) {
                user.setIdRoom(id);
                System.out.println("set room id" + id + " to user: " + user.getLogin());
                resp.setStatus(200);
                break;
            }
        }
    }
}
