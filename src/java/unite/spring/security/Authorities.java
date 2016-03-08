/*

 */
package unite.spring.security;

import org.springframework.security.core.GrantedAuthority;


public class Authorities implements GrantedAuthority {

    private String username;
    private String authority;
    
    @Override
    public String getAuthority() {  
        return this.authority;
    }
    
    // this should be volunteer.
    public void setAuthority(String Authority){
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }
    
}
