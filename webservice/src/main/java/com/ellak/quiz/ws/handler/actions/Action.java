package com.ellak.quiz.ws.handler.actions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Action implements IAction {

    protected Session session;

    public Action(){
    }

    public void initialize(SessionFactory sessionFactory) {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    public Object execute() {
        return null;
    }

    public void terminate() {
        session.getTransaction().commit();
        session.close();
    }
}
