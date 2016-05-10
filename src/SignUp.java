import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import redis.clients.jedis.Jedis;

/**
 * Created by ${Microsoft} on 10/05/2016.
 */
public class SignUp extends HttpServlet {
    String username;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jedis jedis = new Jedis("localhost");
        if (!jedis.exists(req.getParameter("email"))){
            String tempPersonal=encode(req.getParameter("firstName"),req.getParameter("lastName"),req.getParameter("email"),req.getParameter("password"));
            jedis.set(req.getParameter("email"),tempPersonal);
        }
        else{
            // too sdafeye login begoo already a member
        }
        PrintWriter out = resp.getWriter();
        out.println("<h1>" +req.getParameter("firstName")+ "</h1>");
    }

    public String encode(String first,String last,String email,String pass) {
        return first+"&"+last+"&"+email+"&"+pass;
    }
}
