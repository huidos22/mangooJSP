package com.huidos.mangooo.configuration;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.huidos.mangooo.model.Usuario;
import com.huidos.mangooo.repository.UsuarioRepository;

@Service
public class MangooUserDetailService implements UserDetailsService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	 private HttpServletRequest request;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario userMangooo = usuarioRepository.findByUserName(username);
		if (userMangooo == null) {
			throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		}
		request.getSession().setAttribute("usuarioObjSession", userMangooo);
		return new UserRepositoryUserDetails(userMangooo);
	}

	private final static class UserRepositoryUserDetails extends Usuario implements UserDetails {

		private static final long serialVersionUID = 1L;

		public UserRepositoryUserDetails(Usuario user) {
			super(user);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return AuthorityUtils.createAuthorityList("ROLE_USER");
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;// not for production just to show concept
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;// not for production just to show concept
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;// not for production just to show concept
		}

		@Override
		public boolean isEnabled() {
			return true;// not for production just to show concept
		}
		// getPassword() is already implemented in User.class
	}
}
