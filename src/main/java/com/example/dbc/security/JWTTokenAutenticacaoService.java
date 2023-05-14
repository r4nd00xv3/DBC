package com.example.dbc.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dbc.ApplicationContextLoad;
import com.example.dbc.model.Usuario;
import com.example.dbc.repository.UsuarioRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/*Criar a autenticação e retonar também a autenticação JWT*/
@Service
@Component
public class JWTTokenAutenticacaoService {


	/*Token de validade de 11 dias*/
	private static final long EXPIRATION_TIME = 959990000;

	/*Chave de senha para juntar com o JWT*/
	private static final String SECRET = "ss/-*-*sds565dsd-s/d-s*dsds";
	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_STRING = "Authorization";

	

	public void addAuthentication(HttpServletResponse response, String username) throws Exception {
		
		/*Montagem do Token*/
		
		String JWT = Jwts.builder().
				setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		String token = TOKEN_PREFIX + " " + JWT;
		
		response.addHeader(HEADER_STRING, token);
		
		liberacaoCors(response);
		
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
		
	}

	
	public Authentication getAuthetication(HttpServletRequest request, HttpServletResponse response)throws IOException {

		String token = request.getHeader(HEADER_STRING);

		try {


			if (token != null) {

				String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();

				String user = Jwts.parser().
						setSigningKey(SECRET)
						.parseClaimsJws(tokenLimpo)
						.getBody().getSubject();/*ADMIN ou Alex*/

				if (user != null) {

					Usuario usuario = ApplicationContextLoad.
							getApplicationContext().
							getBean(UsuarioRepository.class).findUserByLogin(user);

					if (usuario != null) {
						return new UsernamePasswordAuthenticationToken(
								usuario.getLogin(),
								usuario.getSenha(),
								usuario.getAuthorities());
					}

				}
			}
		}catch (MalformedJwtException e){
			response.getWriter().write("Token com formato invalido");

			}catch(SignatureException e) {
			response.getWriter().write("Token invalido");
		}catch (ExpiredJwtException e){
		response.getWriter().write("Token Expirado");




	}finally {
			liberacaoCors(response);

		}

		return null;

	}
	
	
	private void liberacaoCors(HttpServletResponse response) {
		
		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		
		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		
		
		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
		
	}
	
	

}
