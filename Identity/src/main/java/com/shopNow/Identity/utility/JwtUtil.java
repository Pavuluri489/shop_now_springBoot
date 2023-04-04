package com.shopNow.Identity.utility;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.shopNow.Identity.Entity.Customer;
import com.shopNow.Identity.dto.Tokens;
import com.shopNow.Identity.repository.CustomerRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {
	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	@Autowired
	private CustomerRepository customerRepo;
	
	public String getUserNameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	public String getRolesFromToken(String token) {
		return getClaimFromToken(token, Claims::getAudience);
	}

	public <T> T getClaimFromToken(String token, Function<Claims,T> claimResolver) {
		final Claims claims=getAllClaimsFromToken(token);
		return claimResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName=getUserNameFromToken(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	


    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

	private boolean isTokenExpired(String token) {
		final Date expiration= getExpirationTimeFromToken(token);
		return expiration.before(new Date());
	}

	private Date getExpirationTimeFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public Tokens generateTokens(UserDetails userDetails) {
		final String refreshToken = refreshToken(userDetails);
		final String accessToken = accessToken(refreshToken);
		return new Tokens(accessToken, refreshToken);
	}

	private String refreshToken(UserDetails userDetails) {
		
		return Jwts.builder()
				
				.setSubject(userDetails.getUsername())
				.setId("refresh")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+10*60*1000))
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getSignKey() {
		 byte[] keyBytes= Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private String accessToken(String refreshToken) {
		String username=getUserNameFromToken(refreshToken);
		Customer customer=customerRepo.findByUserName(username) ;
		
		
		
		return Jwts.builder()
				
				.setAudience(customer.getRoles().stream().map(a->a.getRoleCode()).toList().get(0))
				.setSubject(username)
				.setId("access")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+6*60*1000))
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}

}
