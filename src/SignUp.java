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
        HttpSession session=req.getSession();
        System.out.println(action);
        System.out.println(req.getMethod());
        if (action.equals("signup")) {
            if (!jedis.exists(req.getParameter("email"))) {
                String tempPersonal = encode(req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("email"), req.getParameter("password"));
                jedis.set(req.getParameter("email"), tempPersonal);
                session.setAttribute("email",req.getParameter("email"));
                session.setAttribute("isLogin","true");
                session.setAttribute("user",req.getParameter("firstName"));
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                req.setAttribute("error","You are already a member");
                req.getRequestDispatcher("/login_signup.jsp").forward(req, resp);

            }
        }
        else {
            System.out.println(req.getParameter("email"));
            if (jedis.exists(req.getParameter("email"))) {
                String value = jedis.get(req.getParameter("email"));
                if (getPass(value).equals(req.getParameter("pass"))) {
                    session.setAttribute("email",req.getParameter("email"));
                    session.setAttribute("isLogin","true");
                    session.setAttribute("user",getFirst(jedis.get(req.getParameter("email"))));
                    req.getRequestDispatcher("/index.jsp").forward(req, resp);
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
