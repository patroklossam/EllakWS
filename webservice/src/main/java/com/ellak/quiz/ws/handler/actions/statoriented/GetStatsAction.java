package com.ellak.quiz.ws.handler.actions.statoriented;

import com.ellak.quiz.ws.handler.actions.Action;
import com.ellak.quiz.ws.handler.models.entities.Stat;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GetStatsAction extends Action {
    private List<Stat> stats;
    private long user_id;

    public GetStatsAction(SessionFactory sessionfactory, long user_id){
        super();
        initialize(sessionfactory);

	this.user_id = user_id;
    }

    @Override
    public Object execute(){

	stats = session.createQuery("from Stat where user_id="+user_id).list();

        terminate();

        return stats;

    }
}
