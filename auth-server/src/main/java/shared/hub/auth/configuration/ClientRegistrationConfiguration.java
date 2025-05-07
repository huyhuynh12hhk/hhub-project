package shared.hub.auth.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import shared.hub.auth.security.AppOAuth2GrantType;

import java.util.UUID;

@Configuration
public class ClientRegistrationConfiguration {

    // For simple just use in memory client info,
    // when system need more complex idp, implement persistence client repo with db
    @Bean
    public RegisteredClientRepository registeredClientRepository(
            PasswordEncoder passwordEncoder,
            @Value("${CLIENT_ID:test-client}")
            String clientId,
            @Value("${CLIENT_SECRET:secret}")
            String clientSecret
    ) {
        // @formatter:off
        RegisteredClient registeredClient1 = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(clientId)
                .clientSecret(passwordEncoder.encode(clientSecret))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AppOAuth2GrantType.PASSWORD_GRANT_TYPE)

                .redirectUri("https://oidcdebugger.com/debug")
                .redirectUri("http://127.0.0.1:8888/login/oauth2/code/"+clientId)
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .redirectUri("http://localhost:3000/auth/callback")
                .postLogoutRedirectUri("http://localhost:3000/logged-out")
                .scope("openid")
                .scope("profile")
//                .scope("user.all")
//                .scope("user.read")
//                .scope("user.write")
//                .scope("admin")
                .clientSettings(ClientSettings.builder()
                        .requireProofKey(true)
//                        .requireAuthorizationConsent(true)
                        .build())
                .build();

        // @formatter:on


        return new InMemoryRegisteredClientRepository(registeredClient1);
    }
}
