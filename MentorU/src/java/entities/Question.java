/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author isend_000
 */
public class Question extends  Entity{
    
    int Q_Id;
    int Subj_Id;
    int S_Id;
    String Q_Text;

    public Question() {
         this.setType(Entity.Type.Question);
    }

    public int getQ_Id() {
        return Q_Id;
    }

    public int getSubj_Id() {
        return Subj_Id;
    }

    public int getS_Id() {
        return S_Id;
    }

    public String getQ_Text() {
        return Q_Text;
    }

    public void setQ_Id(int Q_Id) {
        this.Q_Id = Q_Id;
    }

    public void setSubj_Id(int Subj_Id) {
        this.Subj_Id = Subj_Id;
    }

    public void setS_Id(int S_Id) {
        this.S_Id = S_Id;
    }

    public void setQ_Text(String Q_Text) {
        this.Q_Text = Q_Text;
    }
    
    
}
