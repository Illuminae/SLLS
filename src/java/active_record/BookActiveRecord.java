/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package active_record;

import static active_record.DatabaseUtility.getDatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Erik
 */
public class BookActiveRecord extends DatabaseUtility {

    private String status;
    private String isbn;
    private String author;
    private String title;
    private int pub_year;
    private String publisher;
    private int owner;

    public BookActiveRecord(String status, String isbn, String author, String title, int pub_year, String publisher, int owner) {
        this.status = status;
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.pub_year = pub_year;
        this.publisher = publisher;
        this.owner = owner;
    }

    public BookActiveRecord() {
        this.status = "Available";
    }

    /**
     * Returns List with all books
     *
     * @return
     */
    public static ArrayList<BookActiveRecord> getBookList() {
        ArrayList<BookActiveRecord> bookList = new ArrayList<>();
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM BOOKS");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BookActiveRecord e = new BookActiveRecord(rs.getString("STATUS"),
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
    }
    /**
     * 
     * @param isbn
     * @return List with one book
     */
        public static ArrayList<BookActiveRecord> getBookList(String isbn) {
        ArrayList<BookActiveRecord> bookList = new ArrayList<>();
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM BOOKS WHERE ISBN = ?");
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BookActiveRecord e = new BookActiveRecord(rs.getString("STATUS"),
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
    }

        /**
         * 
         * @param user
         * @return List with all books that a user has to deny or accept
         */
    public static ArrayList<BookActiveRecord> getRequestedBookList(RegisteredUserActiveRecord user) {
        ArrayList<BookActiveRecord> myRequestedBookList = new ArrayList<>();
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM BOOKS WHERE OWNER = ? AND STATUS = ?");
            stmt.setInt(1, user.getUser_id());
            stmt.setString(2, "pending");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BookActiveRecord e = new BookActiveRecord(rs.getString("STATUS"),
                        rs.getString("ISBN"), rs.getString("AUTHOR"), rs.getString("TITLE"), rs.getInt("PUB_YEAR"),
                        rs.getString("PUBLISHER"), rs.getInt("OWNER"));
                myRequestedBookList.add(e);
            }
            rs.close();
            stmt.close();

        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
        return myRequestedBookList;

    }
    

    public static ArrayList<BookActiveRecord> getBookList(RegisteredUserActiveRecord user) {
        ArrayList<BookActiveRecord> myBookList = new ArrayList<>();
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM BOOKS WHERE OWNER = ?");
            stmt.setInt(1, user.getUser_id());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BookActiveRecord e = new BookActiveRecord(rs.getString("STATUS"),
                        rs.getString("ISBN"), rs.getString("AUTHOR"), rs.getString("TITLE"), rs.getInt("PUB_YEAR"),
                        rs.getString("PUBLISHER"), rs.getInt("OWNER"));
                myBookList.add(e);
            }
            rs.close();
            stmt.close();

        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
        return myBookList;
    }

    public static boolean isOwner(String isbn, int owner) {
        boolean isOwner = false;
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT OWNER FROM BOOKS WHERE ISBN LIKE ?");
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt("OWNER") == owner) {
                    isOwner = true;
                } else {
                    isOwner = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isOwner;
    }

    public static void setPending(String isbn) {
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            String status = "pending";
            stmt = con.prepareStatement("UPDATE BOOKS SET STATUS = ? WHERE ISBN = ?");
            stmt.setString(1, status);
            stmt.setString(2, isbn);
            int updateCount = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void approveRequest(String isbn) {
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("UPDATE BOOKS SET STATUS = 'borrowed' WHERE ISBN = ?");
            stmt.setString(1, isbn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Pushes data of an BookActiveRecord to DB
     *
     * @return true if successful
     */
    public boolean insert() {
        int updateCount = -1;
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement("INSERT INTO BOOKS (STATUS, ISBN, AUTHOR, TITLE, PUB_YEAR, PUBLISHER, OWNER) VALUES (?,?,?,?,?,?,?)");
                stmt.setString(1, this.status);
                stmt.setString(2, this.isbn);
                stmt.setString(3, this.author);
                stmt.setString(4, this.title);
                stmt.setInt(5, this.pub_year);
                stmt.setString(6, this.publisher);
                stmt.setInt(7, this.owner);
                updateCount = stmt.executeUpdate();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            /* To be implemented: Adding of Tags when new book is added
             if (updateCount == 1) {
             stmt = con.prepareStatement("INSERT INTO BOOKS_TAGS (user_id, zip_code, town, street, house_no, iban) VALUES (?,?,?,?,?,?)");
             stmt.setInt(1, this.user_id);
             stmt.setString(2, this.zip_code);
             stmt.setString(3, this.town);
             stmt.setString(4, this.street);
             stmt.setString(5, this.house_no);
             stmt.setString(6, this.iban);
             updateCount = stmt.executeUpdate();
             } else {
             return false;
             }
             */

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1 == updateCount;

    }
    /**
     * Lets you update all values except ISBN
     * @return boolean true if successful, else false
     */
        public boolean update() {
        int updateCount = -1;
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement("UPDATE BOOKS SET STATUS = ?, AUTHOR = ?, TITLE = ?, PUB_YEAR = ?, "
                        + "PUBLISHER = ?, OWNER = ? WHERE ISBN = ?");
                stmt.setString(1, this.status);
                stmt.setString(7, this.isbn);
                stmt.setString(2, this.author);
                stmt.setString(3, this.title);
                stmt.setInt(4, this.pub_year);
                stmt.setString(5, this.publisher);
                stmt.setInt(6, this.owner);
                updateCount = stmt.executeUpdate();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1 == updateCount;

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.pub_year = year;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return pub_year;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getOwner() {
        return owner;
    }

}
