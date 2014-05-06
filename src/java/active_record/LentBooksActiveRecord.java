/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package active_record;

import static active_record.DatabaseUtility.getDatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Erik
 */
public class LentBooksActiveRecord extends DatabaseUtility {

    private int overdue_fines;
    private Date start_date;
    private int duration;
    private int user_id;
    private int owner;
    private String isbn;
    private boolean active;

    public LentBooksActiveRecord(int overdue_fines, Date start_date, int duration, int user_id, int owner, String isbn, boolean active) {
        this.overdue_fines = overdue_fines;
        this.start_date = start_date;
        this.duration = duration;
        this.user_id = user_id;
        this.owner = owner;
        this.isbn = isbn;
        this.active = active;
    }

    public LentBooksActiveRecord(int user_id, String isbn, int owner) {
        overdue_fines = 0;
        this.start_date = new Date(new GregorianCalendar().getTimeInMillis());
        this.duration = 0;
        this.user_id = user_id;
        this.owner = owner;
        this.isbn = isbn;
        this.active = false;
    }

    public boolean insert() {
        int updateCount = -1;
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement("INSERT INTO LENT_BOOKS (OVERDUE_FINES, START_DATE, DURATION, USER_ID, OWNER, ISBN, ACTIVE) VALUES (?,?,?,?,?,?,?)");
                stmt.setInt(1, this.overdue_fines);
                stmt.setDate(2, this.start_date);
                stmt.setInt(3, this.duration);
                stmt.setInt(4, this.user_id);
                stmt.setInt(5, this.owner);
                stmt.setString(6, this.isbn);
                stmt.setBoolean(7, this.active);
                updateCount = stmt.executeUpdate();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1 == updateCount;

    }

    public static boolean deleteLentBooksActiveRecord(String isbn, int owner) {
        int updateCount = -1;
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement("DELETE FROM LENT_BOOKS WHERE ISBN = ? AND OWNER = ?");
                stmt.setString(1, isbn);
                stmt.setInt(2, owner);
                updateCount = stmt.executeUpdate();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1 == updateCount;

    }

    /**
     * returns data for one book out of lentbooklist
     *
     * @param isbn
     * @return
     */
        public static ArrayList<LentBooksActiveRecord> getBookList(int user_id) {
        ArrayList<LentBooksActiveRecord> bookList = new ArrayList<>();
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM LENT_BOOKS WHERE USER_ID = ? AND ACTIVE = ?");
            stmt.setInt(1, user_id);
            stmt.setBoolean(2, true);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LentBooksActiveRecord e = new LentBooksActiveRecord(rs.getInt("OVERDUE_FINES"),
                        rs.getDate("START_DATE"), rs.getInt("DURATION"), rs.getInt("USER_ID"), rs.getInt("OWNER"),
                        rs.getString("ISBN"), rs.getBoolean("ACTIVE"));
                bookList.add(e);
            }
            rs.close();
            stmt.close();

        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
        return bookList;
    }
        
    public static ArrayList<LentBooksActiveRecord> getBookData(String isbn) {
        ArrayList<LentBooksActiveRecord> bookList = new ArrayList<>();
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM LENT_BOOKS WHERE ISBN = ?");
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LentBooksActiveRecord e = new LentBooksActiveRecord(rs.getInt("OVERDUE_FINES"),
                        rs.getDate("START_DATE"), rs.getInt("DURATION"), rs.getInt("USER_ID"), rs.getInt("OWNER"),
                        rs.getString("ISBN"), rs.getBoolean("ACTIVE"));
                bookList.add(e);
            }
            rs.close();
            stmt.close();

        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
        return bookList;
    }

    public boolean update() {
        int updateCount = -1;
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement("UPDATE LENT_BOOKS SET OVERDUE_FINES = ?, START_DATE = ?, DURATION = ?, USER_ID = ?, "
                        + "OWNER = ?, ISBN = ? , ACTIVE = ? WHERE ISBN = ? AND OWNER = ? AND USER_ID = ?");
                stmt.setInt(1, this.overdue_fines);
                stmt.setDate(2, this.start_date);
                stmt.setInt(3, this.duration);
                stmt.setInt(4, this.user_id);
                stmt.setInt(5, this.owner);
                stmt.setString(6, this.isbn);
                stmt.setBoolean(7, this.active);
                stmt.setString(8, this.isbn);
                stmt.setInt(9, this.owner);
                stmt.setInt(10, this.user_id);
                updateCount = stmt.executeUpdate();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1 == updateCount;

    }
    /*
     * 
     * @param user_id Requesting user
     * @param owner     Book owner  
     * @param ISBN  Book identifier
     * @return The book with the aforementioned attributes
     */
    /*public ArrayList<LentBooksActiveRecord> getBook(int user_id, int owner, String ISBN){
     ArrayList<LentBooksActiveRecord> bookList = new ArrayList<>();
     try {
     Connection con = getDatabaseConnection();
     PreparedStatement stmt = null;
     stmt = con.prepareStatement("SELECT * FROM BOOKS WHERE ISBN = ? AND USER_ID = ? AND OWNER = ?");
     stmt.setString(1, ISBN);
     stmt.setInt(2, user_id);
     stmt.setInt(3, owner);
     ResultSet rs = stmt.executeQuery();
     while (rs.next()) {
     LentBooksActiveRecord e = new LentBooksActiveRecord(rs.getString("STATUS"),
     rs.getString("ISBN"), rs.getString("AUTHOR"), rs.getString("TITLE"), rs.getInt("PUB_YEAR"),
     rs.getString("PUBLISHER"), rs.getInt("OWNER"));
     bookList.add(e);
     }
     rs.close();
     stmt.close();

     } catch (Exception sqle) {
     sqle.printStackTrace();
     }
     return bookList;
     }*/

    public void setOverdue_fines(int overdue_fines) {
        this.overdue_fines = overdue_fines;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getOverdue_fines() {
        return overdue_fines;
    }

    public Date getStart_date() {
        return start_date;
    }

    public int getDuration() {
        return duration;
    }
/**
 * 
 * @return int  
 */
    public int getUser_id() {
        return user_id;
    }

    public int getOwner() {
        return owner;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isActive() {
        return active;
    }

}
