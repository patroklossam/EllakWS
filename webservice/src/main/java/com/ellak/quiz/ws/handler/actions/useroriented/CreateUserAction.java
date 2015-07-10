package com.ellak.quiz.ws.handler.actions.useroriented;

import com.ellak.quiz.ws.handler.actions.Action;
import com.ellak.quiz.ws.handler.models.entities.User;
import org.hibernate.SessionFactory;

public class CreateUserAction extends Action {

    private String username;
    private String passkey;
    private String email;

    public CreateUserAction(SessionFactory sessionfactory, String username, String passkey, String email){
        super();
        initialize(sessionfactory);

        this.username = username;
        this.passkey = passkey;
        this.email = email;
    }

    @Override
    public Object execute(){
        User user = new User();
        user.setUsername(username);
        user.setPasskey(passkey);
        user.setEmail(email);

        session.save(user);

        terminate();
        return user;
    }


}
