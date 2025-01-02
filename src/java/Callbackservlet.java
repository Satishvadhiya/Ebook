import com.user.servlet.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@WebServlet("/callback")
public class Callbackservlet extends HttpServlet {
    private static final String CLIENT_ID = "451002860823-muj36ca2465j2mohn9n5ctfaiv7gvrt3.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_URI = "https://c3ca-152-59-16-193.ngrok-free.app/Ebook/index.jsp";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");

        try {
            NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

            // Exchange code for access token
            GoogleAuthorizationCodeTokenRequest tokenRequest =
                new GoogleAuthorizationCodeTokenRequest(
                    httpTransport, JSON_FACTORY,
                    "https://oauth2.googleapis.com/token", 
                    CLIENT_ID, CLIENT_SECRET, code, REDIRECT_URI
                );
            String accessToken = tokenRequest.execute().getAccessToken();

            // Verify ID token to get user info
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, JSON_FACTORY)
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(accessToken);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String userId = payload.getSubject();
                String email = payload.getEmail();
                String name = (String) payload.get("name");

                response.getWriter().println("User ID: " + userId);
                response.getWriter().println("Email: " + email);
                response.getWriter().println("Name: " + name);
            } else {
                response.getWriter().println("Invalid ID Token");
            }
        } catch (GeneralSecurityException | IOException e) {
            throw new ServletException("Failed to exchange token", e);
        }
    }
}
