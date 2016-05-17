import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import redis.clients.jedis.Jedis;

/**
 * Created by ${Microsoft} on 10/05/2016.
 */
public class SignUp extends HttpServlet {
    String username;
    Jedis jedis = new Jedis("localhost");
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("signup")) {
            if (!jedis.exists(req.getParameter("email"))) {
                String tempPersonal = encode(req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("email"), req.getParameter("password"));
                jedis.set(req.getParameter("email"), tempPersonal);
            } else {
                // too sdafeye Login begoo already a member
            }
            PrintWriter out = resp.getWriter();
            out.println("<h1>" + req.getParameter("firstName") + "</h1>");
        }
        else {

            if (jedis.exists(req.getParameter("email"))) {
                String value = jedis.get(req.getParameter("email"));
                if (getPass(value).equals(req.getParameter("pass"))) {
                    HttpSession session=req.getSession();
                    session.setAttribute("email",req.getParameter("email"));
                    session.setAttribute("isLogin",true);
                }
            }
        }
    }

    public String encode(String first, String last, String email, String pass) {
        return first + "&" + last + "&" + email + "&" + pass;
    }
    public String getFirst(String s){
        return s.split("&")[0];
    }
    public String getLast(String s){
        return s.split("&")[1];
    }
    public String getEmail(String s){
        return s.split("&")[2];
    }
    public String getPass(String s){
        return s.split("&")[3];
    }
}
