package ms.ecommerce.ventas.usuarios.security.controller;

import static ms.ecommerce.ventas.usuarios.security.commons.GlobalMessages.*;
import static ms.ecommerce.ventas.usuarios.security.commons.GobalConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.ecommerce.ventas.usuarios.security.controller.response.ResponseRequest;
import ms.ecommerce.ventas.usuarios.security.dto.LogginDTO;
import ms.ecommerce.ventas.usuarios.security.dto.RefreshTokenRequestDTO;
import ms.ecommerce.ventas.usuarios.security.exceptions.TokenException;
import ms.ecommerce.ventas.usuarios.security.exceptions.TokenRefreshException;
import ms.ecommerce.ventas.usuarios.security.models.Response;
import ms.ecommerce.ventas.usuarios.security.service.ILogginService;
import ms.ecommerce.ventas.usuarios.security.utils.JWTUtils;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(API_AUTH)
@CrossOrigin(origins = ACCESS_CORS)
public class LogginController {

	@Autowired
	private ILogginService logginService;

	@Autowired
	private final UserDetailsService userDetailsService;

	@Autowired
	private final JWTUtils jwtUtils;

	@Autowired
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	@PostMapping(API_USUARIO_LOGGIN)
	public ResponseEntity<?> login(@RequestBody LogginDTO logginDTO) throws TokenException {
		try {
			UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(logginDTO.getUser(),
					logginDTO.getPassword());
			Authentication authentication = authenticationManagerBuilder.getObject().authenticate(upat);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			String token = jwtUtils.generateJwtToken(authentication, false);
			String refeshToken = jwtUtils.generateJwtToken(authentication, true);
			Response response = logginService.findUser(logginDTO); // Se puso de forma temporal, para obtener los
																	// detalles de usuario
			return ResponseEntity.ok(ResponseRequest.builder().data(response.getRowsEntitites())
					.isCorrect(response.getIsCorrect()).isBreakOperation(response.getIsBreakOperation())
					.message(response.getMessage()).code(response.getIsCorrect().equals(IS_CORRECT_FALSE) ? 0 : 1)
					.token(token).refreshToken(refeshToken).build());
		} catch (Exception e) {
			log.error("Error al iniciar sesión: ",e);
			return ResponseEntity.ok(ResponseRequest.builder().isCorrect(IS_CORRECT_FALSE)
					.isBreakOperation(IS_CORRECT_TRUE).message(e.getMessage()).code(0).build());
		}
	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(@RequestBody RefreshTokenRequestDTO refreshTokenRequest) throws Exception {
		try {
			String tokenRequest = refreshTokenRequest.getToken();
			if (jwtUtils.validateJwtToken(tokenRequest)) {
				String token = jwtUtils.generateJwtFromTokenRefresh(tokenRequest);
				String user = jwtUtils.getUserNameFromJwtToken(token);
				UserDetails userDetails = userDetailsService.loadUserByUsername(user);
				Response response = logginService.findUser(userDetails.getUsername()); // Se puso de forma temporal,
																						// para obtener los detalles de
																						// usuario
				return ResponseEntity.ok(
						ResponseRequest.builder().data(response.getRowsEntitites()).isCorrect(response.getIsCorrect())
								.isBreakOperation(response.getIsBreakOperation()).message(response.getMessage())
								.code(response.getIsCorrect().equals(IS_CORRECT_FALSE) ? 0 : 1).token(token).build());
			}
			throw new TokenRefreshException();
		} catch (Exception e) {
			return ResponseEntity.ok(ResponseRequest.builder().isCorrect(IS_CORRECT_FALSE)
					.isBreakOperation(IS_CORRECT_TRUE).message(e.getMessage()).code(0).build());

		}
	}

}
