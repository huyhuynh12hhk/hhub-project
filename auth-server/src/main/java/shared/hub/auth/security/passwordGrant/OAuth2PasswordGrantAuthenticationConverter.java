package shared.hub.auth.security.passwordGrant;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;

import static shared.hub.auth.security.AppOAuth2GrantType.PASSWORD_GRANT_TYPE;

public class OAuth2PasswordGrantAuthenticationConverter implements AuthenticationConverter {


    @Nullable
    @Override
    public Authentication convert(HttpServletRequest request) {
        // grant_type (REQUIRED)
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);

        if (!PASSWORD_GRANT_TYPE.getValue().equals(grantType)) {
            return null;
        }

        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();

        MultiValueMap<String, String> parameters = getParameters(request);

        String scope = parameters.getFirst(OAuth2ParameterNames.SCOPE);

        Set<String> scopes = scope != null ? Set.of(scope.split(" ")) : null;

        return new OAuth2PasswordGrantAuthenticationToken(parameters.getFirst(OAuth2ParameterNames.USERNAME),
                parameters.getFirst(OAuth2ParameterNames.PASSWORD), clientPrincipal, scopes);
    }

    private static MultiValueMap<String, String> getParameters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>(parameterMap.size());
        parameterMap.forEach((key, values) -> {
            if (values.length > 0) {
                for (String value : values) {
                    parameters.add(key, value);
                }
            }
        });
        return parameters;
    }
}