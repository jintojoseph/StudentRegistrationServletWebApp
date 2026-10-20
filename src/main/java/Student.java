import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Student {
    String fullName;
    LocalDate dob;
    String gender;
    String email;
    long phone;
    int classNumber;
    String address;
    Connection connect= null;
    PreparedStatement pstmnt = null;
    int rows = 0;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int register(){
        try {
            connect = JdbcUtil.getDBConnection();
            String sql = "INSERT INTO student (fullName,dob,gender,email,phone,grade,address) VALUES (?,?,?,?,?,?,?)";
            pstmnt = connect.prepareStatement(sql);
            pstmnt.setString(1,fullName);
            pstmnt.setObject(2,dob);
            pstmnt.setString(3,gender);
            pstmnt.setString(4,email);
            pstmnt.setLong(5,phone);
            pstmnt.setInt(6,classNumber);
            pstmnt.setString(7,address);
            rows = pstmnt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
}
