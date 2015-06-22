/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import entities.*;
import java.util.ArrayList;
import java.util.logging.Level;
import org.apache.log4j.Logger;
/**
 *
 * @author isend_000
 */
public class EntityManager {
    
    //метод ищет сущность по параметру с типом String
    public static Entity find(String table, String column, String param) throws  SQLException{
        String myQuery;
        switch(table){
            case "Student":
                Student student = new Student();
                myQuery = String.format("select * from students where %1$s = ''%2%s", column, param);
                ResultSet rs = executeFindQuery(myQuery);
                student.setS_Id(rs.getInt("S_id"));
                student.setS_Name(rs.getString("Name"));
                student.setGr_Id(rs.getInt("Gr_Id"));
                student.setYear(rs.getInt("S_Year"));
                student.setS_Mail(rs.getString("Mail"));
                return student;
            default:
                return null;
        }        
    }
    //метод ищет сущность по параметру с типом Int
    public static Entity findByInt(String table, String column, int param) throws  SQLException{
        String myQuery;
        switch(table){
            case "Student":
                Student student = new Student();
                myQuery = String.format("select * from students where %1$s = ''%2$d", column, param);
                ResultSet rs = executeFindQuery(myQuery);
                student.setS_Id(rs.getInt("S_id"));
                student.setS_Name(rs.getString("Name"));
                student.setGr_Id(rs.getInt("Gr_Id"));
                student.setYear(rs.getInt("S_Year"));
                student.setS_Mail(rs.getString("Mail"));
                return student;
            default:
                return null;
        }        
    }
    //метод регистрирует сущность
    public static Entity register(Entity entity) throws SQLException{   
       String myQuery;
       switch(entity.getType()){
           case Tutor:
               Tutor tutor = (Tutor)entity;
               tutor.setT_id(incrementId("T_Id", "tutors"));               
               myQuery = String.format(                      
                       "insert into tutors (T_Id, T_Name, T_Mail, T_Tel, T_Department) " 
                       + "values ('%1$d', '%2$s', '%3$s', '%4$s');", tutor.getT_id(), tutor.getT_Name(),
                       tutor.getT_Mail(), tutor.getT_Kaf());
               
               executeRegQuery(myQuery); 
               return tutor;
           case Student:
               Student student = (Student)entity;
               student.setS_Id(incrementId("students", "S_Id"));
               myQuery = String.format("insert into students (S_Name, S_Mail, S_Tel, Gr_Id) " + 
                      "values (%1$s, %2$s, %3$s, %4$s);",student.getS_Id(), student.getS_Name(), student.getS_Mail(),
                       student.getGr_Id());
               executeRegQuery(myQuery); 
               return student;         
               
           case Group:
               Group group = (Group)entity;
               group.setGr_Id(incrementId("groups", "Gr_Id"));
               myQuery = String.format("insert into groups (Gr_Name, Spec_Id) values (%1$d, %2$s);",
                       group.getGr_Id(), group.getGr_Name());
               executeRegQuery(myQuery); 
               return group;           
               
           case Subject:
               Subject subject = (Subject)entity;
               subject.setSubj_Id(incrementId("subject", "Subj_Id"));
               myQuery = String.format("insert into subjects (Subj_Name) value (%1$s);",
                       subject.getSubj_Name());
               executeRegQuery(myQuery); 
               return subject;
           case Video:
               Video video = (Video)entity;
               video.setVid_Id(incrementId("videos", "Vid_Id"));
               myQuery = String.format("insert into videos (Vid_Id, Subj_Id, Vid_Name, Vid_Path) " + 
                       "values (%1$d, %2$d, %3$s, %4$s)", video.getVid_Id(), video.getSubj_Id(),
                       video.getVid_Name(), video.getVid_Path());
               executeRegQuery(myQuery); 
               return video;
            case Doc:
               Doc doc = (Doc)entity;
               doc.setDoc_Id(incrementId("docs", "Doc_id"));
               myQuery = String.format("insert into docs (Doc_Id, Subj_Id, Doc_Name, Doc_Path) " + 
                       "values (%1$d, %2$d, %3$s, %4$s)", doc.getDoc_Id(), doc.getSubj_Id(),
                       doc.getDoc_Name(), doc.getDoc_Path());
               executeRegQuery(myQuery); 
               return doc;
            case Question:
               Question question = (Question)entity;
               question.setQ_Id(incrementId("questions", "Q_Id"));
               myQuery = String.format("insert into questions (Q_Id, Q_Text, S_Id, Subj_Id) " + 
                       "values (%1$d, %2$s, %3$d, %4$d)", question.getQ_Id(), question.getQ_Text(),
                       question.getS_Id(), question.getSubj_Id());
               executeRegQuery(myQuery); 
               return question;
            case Answer:
               Answer answer = (Answer)entity;
               answer.setA_Id(incrementId("answers", "A_Id"));
               myQuery = String.format("insert into answers (A_Id, A_Text, T_Id, Subj_Id) " + 
                       "values (%1$d, %2$s, %3$d, %4$d)", answer.getA_Id(), answer.getA_Text(),
                       answer.getA_Id(), answer.getSubj_Id());
               executeRegQuery(myQuery); 
               return  answer;
            case T_User:
               T_User t_User = (T_User)entity;
               t_User.setU_Id(incrementId("T_Users", "U_Id"));
               myQuery = String.format("insert into t_users (U_Id, U_Name, U_Pass) " + 
                      "values (%1$d, %2$s, %3$s);", t_User.getU_Id(), t_User.getU_Name(), t_User.getU_Pass());
               executeRegQuery(myQuery); 
               return  t_User;  
            case S_User:
               S_User s_User = (S_User)entity;
               s_User.setU_Id(incrementId("S_Users", "U_Id"));
               myQuery = String.format("insert into t_users (U_Id, U_Name, U_Pass) " + 
                      "values (%1$d, %2$s, %3$s);", s_User.getU_Id(), s_User.getU_Name(), s_User.getU_Pass());
               executeRegQuery(myQuery); 
               return  s_User;    
                default:
            return null;
       }
    }       
    //метод получает DataSource из бд    
    private static MysqlDataSource getDataSource(){
        
        Properties props = new Properties();
        FileInputStream fis = null;
        MysqlDataSource ds = null;
        
        try{
            fis = new FileInputStream("db.properties");
            props.load(fis);
            ds = new MysqlDataSource();
            ds.setURL(props.getProperty("MYSQL_DB_URL"));
            ds.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            ds.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
        }
        catch(IOException ex){
            
        }
        return ds;
    }
    //метод выполняет запрос на регистрауцию
    private static void executeRegQuery(String query){
        Connection conn = null;
        PreparedStatement stm = null;
        MysqlDataSource ds;
        try{
            ds = getDataSource();
            conn  = ds.getConnection();
            stm = conn.prepareStatement(query);
            stm.executeUpdate(query);
        }
        catch(SQLException ex){
            
        }
        finally{
            try {                
                if(stm != null) stm.close();
                if(conn != null) conn.close();
                } 
            catch (SQLException e) {
                    //logger.debug("Ошибка SQL в методе executeRegQuery");
                }
        }
    }
    //метод выполняет поисковый запрос
    private static ResultSet executeFindQuery(String query){
        Connection conn = null;
        PreparedStatement stm = null;
        MysqlDataSource ds;
        ResultSet rs = null;
        try{
            ds = getDataSource();
            conn  = ds.getConnection();
            stm = conn.prepareStatement(query);
            rs  = stm.executeQuery(query);
            
            return rs;
        }
        catch(SQLException ex){
            //logger.debug("Ошибка SQL в методе executeFindQuery");
        }
        finally{
            try {  
                if(rs != null) rs.close();
                if(stm != null) stm.close();
                if(conn != null) conn.close();
                } 
            catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return  null;
    }
    //метод увеличивает id на единицу
    private static int incrementId(String column, String table){
        
        String query = String.format("select %1$s from %2$s order by %3$s desc limit 1;", 
                column, table, column);
        Connection conn = null;        
        Statement stm = null;
        MysqlDataSource ds;
        ResultSet rs = null;
        try{
            ds = getDataSource();
            conn  = ds.getConnection();            
            stm = conn.createStatement();
            rs = stm.executeQuery(query);
            int id = -1;
            while (rs.next()){
                id = rs.getInt(column) + 1;  
                break;
            }
            return id;
        }
        catch(SQLException ex){
            //logger.debug("Ошибка SQL в методе incrementId");
        }
        finally{
            try {    
                if(rs != null) rs.close();
                if(stm != null) stm.close();
                if(conn != null) conn.close();
                } catch (SQLException e) {
                    //logger.debug("Ошибка SQL в методе executeRegQuery");
                }
        }
        return -1;
    }
    //метод выполняет поиск аккаунта студента или преподавателя в бд
    public  static Student getStudent (int id) throws SQLException{        
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;
            Student s = new Student();
            String sql = "select * from vclassroom.students where S_Id = '" + id +"';";
            rs = stm.executeQuery(sql);
            while (rs.next()){                
                s.setS_Id(rs.getInt("S_Id"));
                s.setS_Mail(rs.getString("Mail"));
                s.setS_Name(rs.getString("Name"));
                s.setYear(rs.getInt("S_Year"));
                s.setGr_Id(rs.getInt("Gr_Id"));
            }
            return s;
        }
        catch (ClassNotFoundException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }  
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    public static ResultSet findAll(String table){
        String myQuery = String.format("select * from %1$s", table);
        ResultSet rs = executeFindQuery(myQuery);
        return rs;
    }
    public static S_User getUser (String login, String pass){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;
            S_User u = new S_User();
            String sql = "select * from vclassroom.s_users where U_Name = '" + login + "' and U_Pass = '" + pass +"';";
            rs = stm.executeQuery(sql);
            while (rs.next()){                
                u.setU_Id(rs.getInt("U_Id"));
                u.setU_Name(rs.getString("U_Name"));
                u.setU_Pass(rs.getString("U_Pass"));
            }
            return u;
        }
        catch (ClassNotFoundException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }  
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    public static Group getGroup (int id){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;
            Group g = new Group();
            String sql = "select * from vclassroom.groups where Gr_Id = '" + id +"';";
            rs = stm.executeQuery(sql);
            while (rs.next()){                
                g.setGr_Id(rs.getInt("Gr_Id"));
                g.setGr_Name(rs.getString("Gr_Name"));
            }
            return g;
        }
        catch (ClassNotFoundException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }  
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    static Object findUser(S_User user, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static ArrayList<Doc> getDocs(){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        ArrayList<Doc> list = new ArrayList<>();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;            
            String sql = "select * from vclassroom.docs";
            rs = stm.executeQuery(sql);
            while (rs.next()){     
                Doc d = new Doc();
                d.setDoc_Id(rs.getInt("Doc_Id"));
                d.setSubj_Id(rs.getInt("Subj_Id"));
                d.setDoc_Name(rs.getString("Doc_Name"));
                d.setDoc_Path(rs.getString("Doc_pass"));
                list.add(d);
            }
            return list;
        }
        catch (ClassNotFoundException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }  
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public static ArrayList<Video> getVids(){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        ArrayList<Video> list = new ArrayList<>();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;            
            String sql = "select * from vclassroom.videos";
            rs = stm.executeQuery(sql);
            while (rs.next()){     
                Video v = new Video();
                v.setVid_Id(rs.getInt("Vid_Id"));
                v.setSubj_Id(rs.getInt("Subj_Id"));
                v.setVid_Name(rs.getString("Video"));
                v.setVid_Path(rs.getString("Vid_Pass"));
                list.add(v);
            }
            return list;
        }
        catch (ClassNotFoundException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }  
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public static ArrayList<Subject> getSubjects(){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        ArrayList<Subject> list = new ArrayList<>();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;            
            String sql = "select * from vclassroom.subjects";
            rs = stm.executeQuery(sql);
            while (rs.next()){     
                Subject s = new Subject();
                s.setSubj_Id(rs.getInt("Subj_Id"));
                s.setSubj_Name(rs.getString("Subj_Name"));               
                list.add(s);
            }
            return list;
        }
        catch (ClassNotFoundException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }  
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    public static void updateQuestions(String question, int S_Id, int Subj_Id){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;            
            String sql = "insert into vclassroom.questions (Q_Text, S_Id, Subj_Id) values ('" + question + "' , " + S_Id + " , " + Subj_Id + ");";
            stm.executeUpdate(sql);            
        }
        catch (ClassNotFoundException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }  
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static Subject findSubjectByName(String name){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        Subject s = null;
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;            
            String sql = "select * from vclassroom.subjects where Subj_Name = '" + name + "';";
            rs = stm.executeQuery(sql);
            while (rs.next()){     
                s = new Subject();
                s.setSubj_Id(rs.getInt("Subj_Id"));
                s.setSubj_Name(rs.getString("Subj_Name"));               
            }
            return s;
        }
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);                
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static Answer getAnswer(int id){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
       
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;            
            String sql = "select * from vclassroom.answers where Q_Id = '" + id + "';";
            rs = stm.executeQuery(sql);
            Answer a = new Answer();
            while (rs.next()){                    
                a.setA_Id(rs.getInt("A_Id"));
                a.setA_Text(rs.getString("A_Text"));
                a.setSubj_Id(rs.getInt("Subj_Id"));
                a.setT_Id(rs.getInt("T_Id"));
                a.setQ_Id(rs.getInt("Q_Id"));
            }
            return a;
        }
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);                
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static ArrayList<Question> getQuestions(int id){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера         
        ArrayList<Question> list = new ArrayList<>();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;            
            String sql = "select * from vclassroom.questions where S_Id = '" + id + "';";
            rs = stm.executeQuery(sql);
            while (rs.next()){     
                Question q = new Question();
                q.setQ_Id(rs.getInt("Q_Id"));
                q.setQ_Text(rs.getString("Q_Text"));
                q.setSubj_Id(rs.getInt("Subj_Id"));
                q.setS_Id(rs.getInt("S_Id"));
                list.add(q);
            }
            return list;
        }
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);                
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static T_User getTUser (String login, String pass){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;
            T_User u = new T_User();
            String sql = "select * from vclassroom.t_users where U_Name = '" + login + "' and U_Pass = '" + pass +"';";
            rs = stm.executeQuery(sql);
            while (rs.next()){                
                u.setU_Id(rs.getInt("U_Id"));
                u.setU_Name(rs.getString("U_Name"));
                u.setU_Pass(rs.getString("U_Pass"));
            }
            return u;
        }
        catch (ClassNotFoundException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }  
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    public  static Tutor getTutor (int id) throws SQLException{        
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;
            Tutor t = new Tutor();
            String sql = "select * from vclassroom.tutors where T_Id = '" + id +"';";
            rs = stm.executeQuery(sql);
            while (rs.next()){                
                t.setT_id(rs.getInt("T_Id"));
                t.setT_Mail(rs.getString("Mail"));
                t.setT_Name(rs.getString("Name"));
                t.setT_Kaf(rs.getString("Department"));
            }
            return t;
        }
        catch (ClassNotFoundException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }  
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    public static ArrayList<Question> getAllQuestions(int id){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        Question q = new Question();
        ArrayList<Question> list = new ArrayList<>();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;            
            String sql = "select * from vclassroom.questions where Subj_Id = " + id + ";";
            rs = stm.executeQuery(sql);
            while (rs.next()){     
                q.setQ_Id(rs.getInt("Q_Id"));
                q.setQ_Text(rs.getString("Q_Text"));
                q.setSubj_Id(rs.getInt("Subj_Id"));
                q.setS_Id(rs.getInt("S_Id"));
                list.add(q);
            }
            return list;
        }
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);                
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void addAnswer(int Subj_Id, int T_Id, int S_Id, String A_Text){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;            
            String sql = "insert into vclassroom.answers ( A_Text, T_Id, Subj_Id, S_Id) values (" + A_Text + "," + T_Id + "," + Subj_Id + "," + S_Id + ");";
            stm.executeUpdate(sql);           
        }
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);                
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public static void addDoc(int Subj_Id, String Doc_Name, String Doc_Path){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;            
            String sql = "insert into vclassroom.docs (Subj_Id, Doc_Name, Doc_pass) values (" + Subj_Id + "," + Doc_Name + "," + Doc_Path + ");";
            stm.executeUpdate(sql);           
        }
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);                
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public static void addVideo(int Subj_Id, String Video, String Vid_Path){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;            
            String sql = "insert into vclassroom.videos (Subj_Id, Video, Vid_Pass) values (" + Subj_Id + "," + Video + "," + Vid_Path + ");";
            stm.executeUpdate(sql);           
        }
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);                
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
     public static ArrayList<Question> getQuestionsBySubj(int id){
        String user = "root";//Логин пользователя 
        String password = "adelaida";//Пароль пользователя 
        String url = "jdbc:mysql://localhost:3306/vclassroom";//URL адрес private 
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера 
        Question q = new Question();
        ArrayList<Question> list = new ArrayList<>();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement();
            ResultSet rs = null;            
            String sql = "select * from vclassroom.questions where Subj_Id = '" + id + "';";
            rs = stm.executeQuery(sql);
            while (rs.next()){     
                q.setQ_Id(rs.getInt("Q_Id"));
                q.setQ_Text(rs.getString("Q_Text"));
                q.setSubj_Id(rs.getInt("Subj_Id"));
                q.setS_Id(rs.getInt("S_Id"));
                list.add(q);
            }
            return list;
        }
        catch (SQLException e){
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, e);                
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
    
    
    
    
