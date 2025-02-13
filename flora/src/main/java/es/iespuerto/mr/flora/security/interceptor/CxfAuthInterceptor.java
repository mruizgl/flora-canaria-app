package es.iespuerto.mr.flora.security.interceptor;

import java.util.List;
import java.util.Map;

import es.iespuerto.mr.flora.security.CustomUserDetails;
import es.iespuerto.mr.flora.security.JwtUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CxfAuthInterceptor extends AbstractPhaseInterceptor<Message> {

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String TOKEN_PREFIX = "Bearer ";

    private JwtUtils jwtUtils;

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public CxfAuthInterceptor() {
        super(Phase.PRE_INVOKE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        Map<String, List<String>> protocolHeaders = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);

        if (protocolHeaders == null || !protocolHeaders.containsKey("Authorization")) {
            throw new SecurityException("No security headers included");
        }

        List<String> authorizationHeaders = protocolHeaders.get("Authorization");
        if (authorizationHeaders == null || authorizationHeaders.isEmpty()) {
            throw new SecurityException("Authorization header is missing");
        }

        String authHeader = authorizationHeaders.get(0);
        String token = authHeader.startsWith(TOKEN_PREFIX) ? authHeader.substring(TOKEN_PREFIX.length()) : authHeader;

        if (!jwtUtils.validateToken(token)) {
            throw new SecurityException("Invalid token");
        }

        String username = jwtUtils.getUsernameFromToken(token);
        List<String> roles = jwtUtils.getRolesFromToken(token);

        if (!roles.contains(ROLE_ADMIN)) {
            throw new SecurityException("Unauthorized");
        }

        CustomUserDetails userDetails = new CustomUserDetails(username, ROLE_ADMIN);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                List.of(new SimpleGrantedAuthority(ROLE_ADMIN))
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}