package com.ellak.quiz.ws.handler;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import com.ellak.quiz.ws.handler.actions.cardoriented.GetCardsAction;
import com.ellak.quiz.ws.handler.actions.useroriented.CreateUserAction;
import com.ellak.quiz.ws.handler.actions.useroriented.GetUserAction;
import com.ellak.quiz.ws.handler.actions.useroriented.LoginAction;
import com.ellak.quiz.ws.handler.models.entities.User;
import com.ellak.quiz.ws.handler.models.entities.Card;
import com.ellak.quiz.ws.handler.util.HibernateUtil;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>A servlet which handles all mysql interactions
 * </p>
 */
public final class HandlerServlet extends HttpServlet {


    private static final Logger log = LoggerFactory.getLogger(HandlerServlet.class);
    private boolean debug = true;

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("Initializing Handler servlet!!");
        super.init(config);

        String debugValue = config.getInitParameter("debug");
        if (debugValue == null) {
          throw new ServletException("debug value not defined");
        }

        debug = Boolean.parseBoolean(debugValue);
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException {

        String method = request.getParameter("method");
        if (method == null) {
            throw new ServletException("method was not specified");
        }

        Object obj = executeMethod(method,request);

        handleReturn(response, method, obj);

    }

    private Object executeMethod(String method, HttpServletRequest request) {
        String login;
        String passkey;
		
		int howMany;
        switch (method) {
            case "new":
                login = request.getParameter("login");
                User user = (User) new GetUserAction(sessionFactory,login).execute();
                if (user == null) {
                    passkey = request.getParameter("passkey");
                    return new CreateUserAction(sessionFactory, login, passkey).execute();
                }
                return null;
            case "connect":
                login = request.getParameter("login");
                passkey = request.getParameter("passkey");

                return new LoginAction(sessionFactory,login,passkey).execute();
            case "getCards":
			
				howMany = request.getParameter("howMany");
				
                return new GetCardsAction(sessionFactory,howMany).execute();
            default:
                return null;
        }

    }


    private void handleReturn(HttpServletResponse response, String method, Object object) throws ServletException {
        String message = "";

        switch (method){
            case "new":
                if (object != null)
                    message += "1"; // user added
                else
                    message += "0"; // user exists
                break;
            case "connect":
                if (object != null)
                    message += ((User) object).getUser_id(); // login success return user id
                else
                    message += "-1"; // login failed
                break;
            case "getCards":
				//TODO: handle return as full json
                break;
            default:
                break;
        }

		if(!method.equals("getCards")){
			try {
				writeJSON(response, message);
			} catch (IOException ioe) {
				throw new ServletException(ioe);
			}
		}else{
			try {
				writeCardsInJSON(response, message);
			} catch (IOException ioe) {
				throw new ServletException(ioe);
			}
		}
    }

    private static void writeJSON(HttpServletResponse response, String status) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter writer = response.getWriter();
        writer.print("{\"return\":\"");
        writer.print(status + "\"");

        writer.println("}");
    }
	
	private static void writeCardsInJSON(HttpServletResponse response, Iterable<Card> cards) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter writer = response.getWriter();
        writer.print("[");
		for(Card card : cards){
			writer.print("{");
			writer.print("\"cid\":\""+card.getCid+"\",");
			writer.print("\"question\":\""+card.getQuestion+"\",");
			writer.print("\"ans1\":\""+card.getAns1+"\",");
			writer.print("\"ans2\":\""+card.getAns2+"\",");
			writer.print("\"ans3\":\""+card.getAns3+"\",");
			writer.print("\"ans4\":\""+card.getAns4+"\",");
			writer.print("\"flag1\":\""+card.getFlag1+"\",");
			writer.print("\"flag2\":\""+card.getFlag2+"\",");
			writer.print("\"flag3\":\""+card.getFlag3+"\",");
			writer.print("\"flag4\":\""+card.getFlag4+"\",");
			writer.print("\"category\":\""+card.getCategory+"\"");
			writer.print("},");
		}
        writer.println("]");
    }


    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException {
        doGet(request, response);
    }

    @Override
    public String toString() {
        return "HandlerServlet";
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}