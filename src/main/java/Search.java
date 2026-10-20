import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/search")
public class Search extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get parameter from query string (e.g., ?target=John)
        String target = request.getParameter("target");
        if (target == null || target.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Target name parameter is missing.");
            return;
        }
        String fullName = target.trim();

        Connection connect = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        List<Student> studentList = new ArrayList<>();

        try {
            // 2. Open connection using your utility class
            connect = JdbcUtil.getDBConnection();
            String sql = "SELECT * FROM student WHERE fullname = ?";

            // 3. Prepare statement and bind variable (WITHOUT quotes)
            pstmnt = connect.prepareStatement(sql);
            pstmnt.setString(1, fullName);

            // 4. Execute query and process result set
            rs = pstmnt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setFullName(rs.getString("fullname"));

                // Handle LocalDate conversion from SQL Date
                java.sql.Date sqlDob = rs.getDate("dob");
                if (sqlDob != null) {
                    student.setDob(sqlDob.toLocalDate());
                }

                student.setGender(rs.getString("gender"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getLong("phone"));
                student.setClassNumber(rs.getInt("grade"));
                student.setAddress(rs.getString("address"));
                studentList.add(student);
            }

            // 5. Attach student object to request and forward to JSP
            request.setAttribute("students", studentList);
            request.getRequestDispatcher("studentdetails.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        } finally {
            // 6. Close ResultSet manually, then use your utility to close connection and statement
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                JdbcUtil.closeResource(connect, pstmnt);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}