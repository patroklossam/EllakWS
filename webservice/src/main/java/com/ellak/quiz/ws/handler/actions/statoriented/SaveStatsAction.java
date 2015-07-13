package com.ellak.quiz.ws.handler.actions.statoriented;

import com.ellak.quiz.ws.handler.actions.Action;
import com.ellak.quiz.ws.handler.models.entities.Stat;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SaveStatsAction extends Action {
    private List<Stat> stats;

    private long user_id;
    private String category;
    private Double score;
    private boolean found;

    public SaveStatsAction(SessionFactory sessionfactory, long user_id, String category, double score){
        super();
        initialize(sessionfactory);
	
	this.found=false;
	this.user_id = user_id;
	this.category = category;
	this.score = score;
    }

    @Override
    public Object execute(){

	stats = session.createQuery("from Stat where user_id="+user_id).list();

	for(int i=0;i<stats.size();i++){
		Stat stat = (Stat) stats.get(i);
		if(stat.getCategory().equals(this.category)){
			found=true;
			//TODO: update stat
			double new_score = stat.getScore() * stat.getTries() + this.score;
                	stat.setTries(stat.getTries()+1);
               	 	stat.setScore(new_score/stat.getTries());
			
			session.update(stat);
			break;
		}
	}

	if(!found){
		Stat stat = new Stat();
		stat.setUser_id(this.user_id);
		stat.setCategory(this.category);
		stat.setScore(this.score);
		stat.setTries(1);

		session.save(stat);
	}
        terminate();

        return 1;

    }
}
