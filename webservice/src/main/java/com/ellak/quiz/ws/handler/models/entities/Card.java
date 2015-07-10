package com.ellak.quiz.ws.handler.models.entities;

import javax.persistence.*;


@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Long c_id;

    @Column(name = "question")
    private String question;

    @Column(name = "ans1")
    private String ans1;

    @Column(name = "ans2")
    private String ans2;

    @Column(name = "ans3")
    private String ans3;

    @Column(name = "ans4")
    private String ans4;

    @Column(name = "flag1")
    private Boolean flag1;

    @Column(name = "flag2")
    private Boolean flag2;

    @Column(name = "flag3")
    private Boolean flag3;

    @Column(name = "flag4")
    private Boolean flag4;

    @Column(name = "category")
    private String category;

    public Long getC_id() {
        return c_id;
    }

    public void setC_id(Long c_id) {
        this.c_id = c_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public Boolean getFlag1() {
        return flag1;
    }

    public void setFlag1(Boolean flag1) {
        this.flag1 = flag1;
    }

    public Boolean getFlag2() {
        return flag2;
}