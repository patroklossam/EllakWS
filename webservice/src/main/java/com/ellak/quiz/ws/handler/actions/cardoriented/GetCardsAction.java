package com.ellak.quiz.ws.handler.actions.cardoriented;

import com.ellak.quiz.ws.handler.actions.Action;
import com.ellak.quiz.ws.handler.models.entities.Card;
import org.hibernate.SessionFactory;

import java.util.List;

public class GetCardsAction extends Action {
    private List<Card> cards;
    private int howMany;
    private String category;

    public GetUserAction(SessionFactory sessionfactory, int howMany, String category){
        super();
        initialize(sessionfactory);

        this.howMany = howMany;
        this.category = category;
    }

    @Override
    public Object execute(){
		
		int size = Integer.parseInt(session.createQuery("select sum(*) from Card").list().get(0).toString());
		
		
		//cards = 
		
		
        terminate();

        return cards;

    }


}
