import com.user.servlet.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/google")
public class ServletLogin extends HttpServlet {
    private static final String CLIENT_ID = "451002860823-muj36ca2465j2mohn9n5ctfaiv7gvrt3.apps.googleusercontent.com";
    private static final String REDIRECT_URI = "https://c3ca-152-59-16-193.ngrok-free.app/Ebook/callback";
    private static final String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String oauthURL = AUTH_URL + 
            "?client_id=" + CLIENT_ID +
            "&redirect_uri=" + REDIRECT_URI +
            "&response_type=code" +
            "&scope=email profile" +
            "&access_type=online";
        response.sendRedirect(oauthURL);
    }
}


