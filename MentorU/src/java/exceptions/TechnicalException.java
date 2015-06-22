/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import java.sql.SQLException;



/**
 *
 * @author isend_000
 */
public class TechnicalException extends  SQLException{
    public TechnicalException(String msg) {
        super(msg);
    } 
    public TechnicalException() {
    }
}
