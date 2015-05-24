package servlets;

import user.User;
import user.UserList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


public class LoginServlet extends HttpServlet {

    UserList userList = UserList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] user = userLoginPass(req.getInputStream());
        int positionInList = userList.isExistsUser(user[0]);
        if (positionInList >= 0 && userList.isCorrectPassword(positionInList, user[1])){
            userList.setUserStatus(positionInList, (byte) 1);
            resp.setStatus(200);
        }
        else if (positionInList < 0){
            userList.addUser(new User(user[0], user[1]));
            resp.setStatus(200);
        }
        else{
            resp.setStatus(401);
        }
    }

    protected String[] userLoginPass(InputStream inputStream) throws IOException {
        if (inputStream.available() <= 0){
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String login = reader.readLine();
        String password = reader.readLine();
        String[] user = new String[]{login, password};
        return user;
    }
}
