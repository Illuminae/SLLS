/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package active_record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Erik
 */
public class RegisteredUserActiveRecord extends DatabaseUtility {

    private String first_name;
    private String last_name;
    private String user_name;
    private String password;
    private int user_type;
    private String zip_code;
    private String town;
    private String street;
    private String house_no;
    private Integer total_fines;
    private String iban;
    private int user_id;

    public RegisteredUserActiveRecord() {
        /**
         * Default user_type = 1 as Admins + Librarians can't register
         */
        this.user_type = 1;
        this.total_fines = 0;
        this.first_name = null;
        this.last_name = null;
        this.user_name = null;
        this.password = null;
        this.zip_code = null;
        this.town = null;
        this.street = null;
        this.house_no = null;
        this.iban = null;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public int getUser_type() {
        /**
         * returns user type 1 = Service-User 2 = Premium-User 3 = Librarian 4 =
         * Administrator
         */
        return user_type;
    }

    public String getZip_code() {
        return zip_code;
    }

    public String getTown() {
        return town;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse_no() {
        return house_no;
    }

    public Integer getTotal_fines() {
        return total_fines;
    }

    public String getIban() {
        return iban;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setUser_type(int i) {
        this.user_type = i;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public void setTotal_fines(Integer total_fines) {
        this.total_fines = total_fines;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public boolean verifyUserdata(String user, String password) {
        boolean isVerified = false;
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT PASSWORD FROM REGISTERED_USERS WHERE USER_NAME = ?");
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String dbpw = rs.getString("PASSWORD");
                if (dbpw.equals(password)) {
                    isVerified = true;
                    return isVerified;
                } 
            } else {
                return isVerified;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isVerified;
    }

    public boolean insert() {
        int i = -1;
        ResultSet generatedKeys;
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement("INSERT INTO REGISTERED_USERS (first_name, last_name, user_name, password) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, this.first_name);
                stmt.setString(2, this.last_name);
                stmt.setString(3, this.user_name);
                stmt.setString(4, this.password);
                i = stmt.executeUpdate();
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    this.user_id = generatedKeys.getInt(1);
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            if (i == 1) {
                stmt = con.prepareStatement("INSERT INTO SERVICE_USERS (user_id, zip_code, town, street, house_no, iban) VALUES (?,?,?,?,?,?)");
                stmt.setInt(1, this.user_id);
                stmt.setString(2, this.zip_code);
                stmt.setString(3, this.town);
                stmt.setString(4, this.street);
                stmt.setString(5, this.house_no);
                stmt.setString(6, this.iban);
                i = stmt.executeUpdate();
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i == 1;

    }

    public boolean delete() {

        return false;
    }
}
