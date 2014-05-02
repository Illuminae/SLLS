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
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

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

    public RegisteredUserActiveRecord(String first_name, String last_name, String user_name, String password, int user_type, String zip_code, String town, String street, String house_no, Integer total_fines, String iban, int user_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_name = user_name;
        this.password = password;
        this.user_type = user_type;
        this.zip_code = zip_code;
        this.town = town;
        this.street = street;
        this.house_no = house_no;
        this.total_fines = total_fines;
        this.iban = iban;
        this.user_id = user_id;
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

    /**
     * returns user type 1 = Service-User 2 = Premium-User 3 = Librarian 4
     * =Administrator
     *
     * @return
     */
    public int getUser_type() {

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

    public static ArrayList<RegisteredUserActiveRecord> getUserList() {
        ArrayList<RegisteredUserActiveRecord> userList = new ArrayList<RegisteredUserActiveRecord>();
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM SERVICE_USERS_FULL");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RegisteredUserActiveRecord e = new RegisteredUserActiveRecord(rs.getString("FIRST_NAME"),
                        rs.getString("LAST_NAME"), rs.getString("USER_NAME"), rs.getString("PASSWORD"), rs.getInt("USER_TYPE"),
                        rs.getString("ZIP_CODE"), rs.getString("TOWN"), rs.getString("STREET"),
                        rs.getString("HOUSE_NO"), rs.getInt("TOTAL_FINES"), rs.getString("IBAN"), rs.getInt("USER_ID"));
                userList.add(e);
            }
            rs.close();
            stmt.close();

        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
        return userList;
    }

    /**
     * Returns List with one item: ActiveRecord of required User
     *
     * @return
     */
    public static ArrayList<RegisteredUserActiveRecord> getUserList(String user_name) {
        ArrayList<RegisteredUserActiveRecord> userList = new ArrayList<RegisteredUserActiveRecord>();
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM SERVICE_USERS_FULL WHERE USER_NAME = ?");
            stmt.setString(1, user_name);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RegisteredUserActiveRecord e = new RegisteredUserActiveRecord(rs.getString("FIRST_NAME"),
                        rs.getString("LAST_NAME"), rs.getString("USER_NAME"), rs.getString("PASSWORD"), rs.getInt("USER_TYPE"),
                        rs.getString("ZIP_CODE"), rs.getString("TOWN"), rs.getString("STREET"),
                        rs.getString("HOUSE_NO"), rs.getInt("TOTAL_FINES"), rs.getString("IBAN"), rs.getInt("USER_ID"));
                userList.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;

    }

    public void verifyUserdata(String user, String password, HttpServletRequest request) {
        boolean isVerified = false;
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement stmt = null;
            ArrayList<RegisteredUserActiveRecord> e = getUserList(user);
            if (e.get(0).password.equals(password)) {
                isVerified = true;
                request.getSession().setAttribute("userType", e.get(0).user_type);
                request.getSession().setAttribute("isVerified", isVerified);
                request.getSession().setAttribute("currentUser", e.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("isVerified", isVerified);
        request.setAttribute("insertSuccess", isVerified);
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
