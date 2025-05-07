package shared.hub.auth.security;

import org.springframework.security.oauth2.core.AuthorizationGrantType;

public class AppOAuth2GrantType {
    public static final AuthorizationGrantType PASSWORD_GRANT_TYPE = new AuthorizationGrantType("password");
}
