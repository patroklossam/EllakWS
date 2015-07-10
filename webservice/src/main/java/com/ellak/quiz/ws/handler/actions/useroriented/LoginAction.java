package com.ellak.quiz.ws.handler.actions.useroriented;

import com.ellak.quiz.ws.handler.actions.Action;
import com.ellak.quiz.ws.handler.models.entities.User;
import org.hibernate.SessionFactory;

import java.util.List;

public class LoginAction extends Action {

    private String login;
    private String passkey;
    public LoginAction(SessionFactory sessionfactory,String login, String passkey){
        super();
        initialize(sessionfactory);

        this.login = login;
        this.passkey = passkey;
    }

    @Override
    public Object execute(){
        List userlist = session.createQuery("FROM User WHERE username = '"+login +"' AND passkey='"+passkey+"'").list();
        User user = null;
        if(userlist.size() != 0)
            user = (User) userlist.get(0);
        terminate();
        return user;
    }

}
