package shared.hub.auth.security.passwordGrant;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.io.Serial;
import java.util.Set;

import static shared.hub.auth.security.AppOAuth2GrantType.PASSWORD_GRANT_TYPE;

@Getter
public class OAuth2PasswordGrantAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {
    @Serial
    private static final long serialVersionUID = 2222222226504832L;
    private final String username;
    private final String password;
    private final String clientId;
    private final Set<String> scopes;


    public OAuth2PasswordGrantAuthenticationToken(String username, String password, Authentication clientPrincipal, Set<String> scopes) {
        super(PASSWORD_GRANT_TYPE, clientPrincipal, null);
        this.password = password;
        this.username = username;
        this.clientId = clientPrincipal.getName();
        this.scopes = scopes;
    }
}