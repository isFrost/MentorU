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
public class User extends Entity {
    int U_Id;
    String U_Name;
    String U_Pass;

    public User() {
        this.setType(Entity.Type.User);
    }

    public int getU_Id() {
        return U_Id;
    }

    public String getU_Name() {
        return U_Name;
    }

    public String getU_Pass() {
        return U_Pass;
    }

    public void setU_Id(int U_Id) {
        this.U_Id = U_Id;
    }

    public void setU_Name(String U_Name) {
        this.U_Name = U_Name;
    }

    public void setU_Pass(String U_Pass) {
        this.U_Pass = U_Pass;
    }
    
    
}
