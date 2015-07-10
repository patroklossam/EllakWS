package com.ellak.quiz.ws.handler.actions.useroriented;

import com.ellak.quiz.ws.handler.actions.Action;
import com.ellak.quiz.ws.handler.models.entities.User;
import org.hibernate.SessionFactory;

import java.util.List;

public class GetUserAction extends Action {
    private User user;
    private String testlogin;

    public GetUserAction(SessionFactory sessionfactory, String login){
        super();
        initialize(sessionfactory);

        testlogin = login;
    }

    @Override
    public Object execute(){
        List userlist = session.createQuery("FROM User WHERE username = '"+testlogin+"'").list();

        if(userlist.size()!=0){
            user = (User) userlist.get(0);
        }else{
            user = null;
        }

        terminate();

        return user;

    }


}
