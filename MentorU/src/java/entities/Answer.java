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
public class Answer extends Entity{
    int A_Id;
    int Subj_Id;
    int T_Id;
    int Q_Id;
    String A_Text;

    public Answer() {
         this.setType(Entity.Type.Answer);
    }

    public int getA_Id() {
        return A_Id;
    }

    public int getSubj_Id() {
        return Subj_Id;
    }

    public int getT_Id() {
        return T_Id;
    }

    public String getA_Text() {
        return A_Text;
    }

    public void setA_Id(int A_Id) {
        this.A_Id = A_Id;
    }

    public void setSubj_Id(int Subj_Id) {
        this.Subj_Id = Subj_Id;
    }

    public void setT_Id(int T_Id) {
        this.T_Id = T_Id;
    }

    public void setA_Text(String A_Text) {
        this.A_Text = A_Text;
    }

    public int getQ_Id() {
        return Q_Id;
    }

    public void setQ_Id(int Q_Id) {
        this.Q_Id = Q_Id;
    }
    
    
}
