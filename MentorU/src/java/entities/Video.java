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
public class Video extends Entity{
    int Vid_Id;
    int Subj_Id;
    String Vid_Name;
    String Vid_Path;

    public Video() {
         this.setType(Entity.Type.Video);
    }

    public int getVid_Id() {
        return Vid_Id;
    }

    public int getSubj_Id() {
        return Subj_Id;
    }

    public String getVid_Name() {
        return Vid_Name;
    }

    public String getVid_Path() {
        return Vid_Path;
    }

    public void setVid_Id(int Vid_Id) {
        this.Vid_Id = Vid_Id;
    }

    public void setSubj_Id(int Subj_Id) {
        this.Subj_Id = Subj_Id;
    }

    public void setVid_Name(String Vid_Name) {
        this.Vid_Name = Vid_Name;
    }

    public void setVid_Path(String Vid_Path) {
        this.Vid_Path = Vid_Path;
    }
    
    
}
