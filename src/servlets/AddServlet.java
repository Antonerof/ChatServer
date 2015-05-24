package servlets;

import message.Message;
import message.MessageList;
import user.User;
import user.UserList;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MessageList msgList = MessageList.getInstance();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{
		Message m = null;

		//			m = Message.readFromStream(req.getInputStream());
		m = Message.readFromStreamXML(req.getInputStream());
		User user = UserList.getUserByName(m.getFrom());
		m.setIdRoom(user.getIdRoom());

		if (m == null) {
			resp.setStatus(400); // bad request
			return;
		} else
			msgList.add(m);
	}


}
