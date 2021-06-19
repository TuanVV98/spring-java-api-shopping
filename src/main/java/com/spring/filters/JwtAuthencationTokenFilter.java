package com.spring.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


import com.spring.util.security.JwtTokenUtil;






public class JwtAuthencationTokenFilter extends OncePerRequestFilter{

	private static final String AUTH_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

//	private static final Logger logger = LoggerFactory.getLogger(JwtAuthencationTokenFilter.class);
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// token gửi lên có dạng : Authorization: Bearer <token>
		
		String token = request.getHeader(AUTH_HEADER);
		System.out.println("token : "+token);
		
		// Kiểm tra xem header Authorization có chứa thông tin jwt không
		
        if (token != null && token.startsWith(BEARER_PREFIX)) {
        	System.out.println("token true !!");
        	token = token.substring(7);
        }
        
//        System.out.println("token substring :"+token);
        String email = jwtTokenUtil.getUsernameFromToken(token);
        System.out.println("email : "+email);
        
        
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        	System.out.println("");
        	
        	// Lấy thông tin người dùng từ username 
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

            if (jwtTokenUtil.validToken(token)) {
            	System.out.println("valid token true:"+userDetails.getAuthorities());
            	
            	// Nếu người dùng hợp lệ, set thông tin cho Seturity Context
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, 
                		userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
                
            }
        }

        filterChain.doFilter(request, response);

	}

//	private String parseJwt(HttpServletRequest request) {
//		String headerAuth = request.getHeader("Authorization");
//
//		System.out.println("headerAuth:"+ headerAuth);
//		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
//			return headerAuth.substring(7, headerAuth.length());
//		}
//
//		return null;
//	}

}
