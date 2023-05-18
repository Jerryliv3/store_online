package ms.ecommerce.ventas.usuarios.security.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.userdetails.

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.ecommerce.ventas.usuarios.security.dto.RolDTO;
import ms.ecommerce.ventas.usuarios.security.dto.UsuarioDTO;
import ms.ecommerce.ventas.usuarios.security.models.Response;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final ILogginService logginService;

	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			Response response = logginService.findUser(username);
			log.info("response {}",response);
			if ( response.getIsCorrect().equals("true") ) {		
				usuarioDTO = (UsuarioDTO) response.getRowsEntitites();
				log.info("usuarioDTO {}",response.getRowsEntitites());
			} else {
				throw new UsernameNotFoundException("Usuario no encontrado!");
			}
			
			return User
					.builder()
					.username(usuarioDTO.getLoginUsuario())
					.password(usuarioDTO.getPassword())
					.authorities(getAuthorities(usuarioDTO.getListRoles()))
					.build();
			
		} catch (Exception e) {
			throw new UsernameNotFoundException("Error al cargar usuario!");
		}

	}
	
	private List<GrantedAuthority> getAuthorities(List<RolDTO> listRoles){
		List<GrantedAuthority> authorityList = new ArrayList<>();
		for (RolDTO authorityEntity : listRoles) {
			authorityList.add(new SimpleGrantedAuthority(authorityEntity.getRol()));
		}
		log.info("authorityList {}",authorityList);
		return authorityList;
	}	
}
