package servlets;

import user.User;
import user.UserList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ListUsersServlet extends HttpServlet {
    private final long TIMEOUT = 30000;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OutputStream outputStream = resp.getOutputStream();
        checkUsersStatus();
        sendUsersList(outputStream);

    }

    private void sendUsersList(OutputStream outputStream) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        int i = 1;
        for (User user : UserList.getList()) {
            writer.write(i++ + ". " + user.getLogin());
            switch (user.getStatus()){
               case 0: {
                   writer.write(" - offline");
                   break;
               }
               case 1: {
                   writer.write(" - online");
                   break;
               }
            }
            writer.write(", room: " + user.getIdRoom());
            writer.newLine();

        }
        writer.flush();
        writer.close();
    }

    private void checkUsersStatus(){
        UserList userList = UserList.getInstance();
        userList.checkUsersStatus(TIMEOUT);
    }
}
