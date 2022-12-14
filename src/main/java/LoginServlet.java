import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = { "/LoginServlet" },
        initParams = {
                @WebInitParam(name = "user", value = "Javul"),
                @WebInitParam(name = "password", value = "javul123")
        }
)

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");

        String nameValidate = "^[A-Z]{1}[a-z]{2,}";
        String passwordValidate = "^(?=.*[A-z])(?=.*[0-9])([a-zA-Z0-9@._-]).{8,}$";
        if (user.equals(userID) && user.matches(nameValidate) && password.equals(password) && password.matches(passwordValidate)){
            req.setAttribute("user",user);
            req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out  = resp.getWriter();
            out.println("<font color = red> Either username or password is wrong</font>");
            rd.include(req, resp);
        }

    }

}
