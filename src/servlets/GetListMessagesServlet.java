package servlets;

import message.Message;
import message.MessageList;
import user.User;
import user.UserList;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetListMessagesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private MessageList msgList = MessageList.getInstance();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException 
	{
		OutputStream os = resp.getOutputStream();
		String fromStr = req.getParameter("from");
		String username = req.getParameter("username");
		int from = Integer.parseInt(fromStr);
		Message m;
		long lastConnect = System.currentTimeMillis();
		writeUserLastLoginTime(lastConnect, username);
		List<Message> list = msgList.get();
		User user = UserList.getUserByName(username);
		
		for (int i = from; i < list.size(); i++) {
			m = list.get(i);
			if (m.getTo().equals(username)){
				m.writeToStreamXML(os);
				break;
			}
			if (m.getTo().equals("") && (m.getIdRoom() == user.getIdRoom())){
				m.writeToStreamXML(os);
				break;
			}
		}
	}

	private void writeUserLastLoginTime(long lastConnect, String name){
		UserList userList = UserList.getInstance();
		userList.writeLastloginTime(lastConnect, name);
	}
}
