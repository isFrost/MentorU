/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import java.sql.SQLException;

/**
 *
 * @author Vladimir Smolko
 */
public class PersistException extends Throwable{
    public PersistException(String e) throws SQLException{
        throw new SQLException(e);
    }

    public PersistException(Exception e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
