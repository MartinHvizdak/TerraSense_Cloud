package sep4.terrasense_cloud.jwt;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.*;

import java.io.IOException;

@WebFilter("/*")
public class JwtAuthenticationFilter implements Filter {

    private static final String SECRET_KEY = "i7W+QdF3hL6BUI8OeApYvP7a1b6J/3p/A+ECfHZDaRk="; // Replace with your own secret key
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();
        System.out.println("new request: "+path);
        // Exclude public endpoints from authentication
        if (path.startsWith("/public")) {

            filterChain.doFilter(request, response);
            return;
        }

        // Retrieve the token from the request header
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            token = token.replace(TOKEN_PREFIX, "");
            try {
                // Validate the token and extract claims
                Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
                request.setAttribute("claims", claims); // Make claims available to other parts of the application
                filterChain.doFilter(request, response);
            } catch (JwtException e) {
                // Token is invalid
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            // Token is missing
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    // Other methods of the Filter interface (init() and destroy()) can be left empty.
}
