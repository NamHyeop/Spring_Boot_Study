package connected.communication.config.security;

import connected.communication.config.ConfigSecurity.CustomUserDetails;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    private String type;
    private CustomUserDetails principal;

    public CustomAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String type, CustomUserDetails principal) {
        super(authorities);
        this.type = type;
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return principal;
    }

    @Override
    public Object getPrincipal() {
         throw new UnsupportedOperationException();
    }

    public String getType(){
        return type;
    }
}
