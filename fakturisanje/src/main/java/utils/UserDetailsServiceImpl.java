package utils;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.poslovna.fakturisanje.models.Korisnik;
import com.poslovna.fakturisanje.repositories.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRrepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		{

	        final Korisnik user = userRrepository.findByUsername(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("user not found");
	        }

	        return new UserDetails() {
	            @Override
	            public Collection<? extends GrantedAuthority> getAuthorities() {
	                final Collection<GrantedAuthority> authorities = new ArrayList<>();
	                authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
	                return authorities;
	            }

	            @Override
	            public String getPassword() {
	                return user.getPassword();
	            }

	            @Override
	            public String getUsername() {
	                return user.getUsername();
	            }

	            @Override
	            public boolean isAccountNonExpired() {
	                return true;
	            }

	            @Override
	            public boolean isAccountNonLocked() {
	                return true;
	            }

	            @Override
	            public boolean isCredentialsNonExpired() {
	                return true;
	            }

				@Override
				public boolean isEnabled() {
					return true;
				}

	        };

	    }
	}

}
