package com.ellak.quiz.ws.handler.actions;

import org.hibernate.SessionFactory;

public interface IAction {
    public void initialize(SessionFactory sessionFactory);
    public Object execute();
    public void terminate();
}
