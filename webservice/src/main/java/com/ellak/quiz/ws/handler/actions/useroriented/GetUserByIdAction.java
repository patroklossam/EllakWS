package com.ellak.quiz.ws.handler.actions.useroriented;

import com.ellak.quiz.ws.handler.actions.Action;
import com.ellak.quiz.ws.handler.models.entities.User;
import org.hibernate.SessionFactory;

import java.util.List;

public class GetUserByIdAction extends Action {
    private User user;
    private long user_id;

    public GetUserByIdAction(SessionFactory sessionfactory, long user_id){
        super();
        initialize(sessionfactory);

        this.user_id = user_id;
    }

    @Override
    public Object execute(){
        List userlist = session.createQuery("FROM User WHERE user_id = "+user_id).list();

        if(userlist.size()!=0){
            user = (User) userlist.get(0);
        }else{
            user = null;
        }

        terminate();

        return user;

    }


}
