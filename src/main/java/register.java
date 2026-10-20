import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/register")
public class register extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fullName = request.getParameter("fullname");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        long phone = Long.parseLong(request.getParameter("phone"));
        int classNumber = Integer.parseInt(request.getParameter("grade"));
        String address = request.getParameter("address");

        Student student = new Student();
        student.setFullName(fullName);
        student.setDob(dob);
        student.setGender(gender);
        student.setEmail(email);
        student.setAddress(address);
        student.setClassNumber(classNumber);
        student.setPhone(phone);

        int row = student.register();
        HttpSession session = request.getSession();
        session.setAttribute("fullName",fullName);
        String contextPath = request.getContextPath();
        if (row != 0) {
            response.sendRedirect(contextPath + "/success.jsp");
        } else {
            response.sendRedirect(contextPath + "/failed.jsp");
        }
    }
}
