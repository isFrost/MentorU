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
public class Entity {
    
    public Type type;

    public Entity() {
        this.type = null;
    }
    
    public enum Type{
        Tutor,
        Student,        
        Group,
        Video,
        Doc,
        Subject,
        S_User,
        T_User,
        Question,
        Answer,
        User
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
}
