package com.yash.ecommerce.service;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.yash.ecommerce.controller.HomeController;
import com.yash.ecommerce.entity.Authorities;
import com.yash.ecommerce.entity.User;
import com.yash.ecommerce.exception.UserCustomException;
import com.yash.ecommerce.model.ServerResponse;
import com.yash.ecommerce.repository.UserRepository;
import com.yash.ecommerce.util.JwtUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebMvcTest(value = HomeService.class, includeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtUtil.class) 
		})
public class HomeServiceTest {
	
	@Autowired
	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@MockBean
	MyUserDetailService myUserDetailService;
	
	@Autowired
	HomeService service;
	
	@MockBean
	SendMailService mailService;
	
	@MockBean
	UserRepository userRepository;
	
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}
	
	@Test
	public void addUserTest() {
		User user = new User();
		user.setUserName("tanay");
		user.setEmail("tanay@gmail.com");
		Authorities role = new Authorities();
		role.setUserName("tanay");
		role.setAuthority("USER");
		user.setRoles(List.of(role));
		user.setAddress(null);
		user.setUserType("USER");
		user.setPassword("(Tanay@12)");

		when(userRepository.findByUserEmail("tanay@gmail.com")).thenReturn(Optional.empty());
		when(userRepository.save(user)).thenReturn(user);
		
		ServerResponse res = null;
		try {
			 HttpServletRequest req = new HttpServletRequest() {
				
				@Override
				public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
						throws IllegalStateException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public AsyncContext startAsync() throws IllegalStateException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void setAttribute(String name, Object o) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void removeAttribute(String name) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public boolean isSecure() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean isAsyncSupported() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean isAsyncStarted() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public ServletContext getServletContext() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public int getServerPort() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public String getServerName() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getScheme() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public RequestDispatcher getRequestDispatcher(String path) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public int getRemotePort() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public String getRemoteHost() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getRemoteAddr() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getRealPath(String path) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public BufferedReader getReader() throws IOException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getProtocol() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String[] getParameterValues(String name) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Enumeration<String> getParameterNames() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Map<String, String[]> getParameterMap() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getParameter(String name) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Enumeration<Locale> getLocales() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Locale getLocale() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public int getLocalPort() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public String getLocalName() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getLocalAddr() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public ServletInputStream getInputStream() throws IOException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public DispatcherType getDispatcherType() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getContentType() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public long getContentLengthLong() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public int getContentLength() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public String getCharacterEncoding() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Enumeration<String> getAttributeNames() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Object getAttribute(String name) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public AsyncContext getAsyncContext() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public <T extends HttpUpgradeHandler> T upgrade(Class<T> httpUpgradeHandlerClass)
						throws IOException, ServletException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public void logout() throws ServletException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void login(String username, String password) throws ServletException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public boolean isUserInRole(String role) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean isRequestedSessionIdValid() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean isRequestedSessionIdFromUrl() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean isRequestedSessionIdFromURL() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean isRequestedSessionIdFromCookie() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public Principal getUserPrincipal() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public HttpSession getSession(boolean create) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public HttpSession getSession() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getServletPath() {
					// TODO Auto-generated method stub
					return "";
				}
				
				@Override
				public String getRequestedSessionId() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public StringBuffer getRequestURL() {
					// TODO Auto-generated method stub
					return new StringBuffer("http://localhost:8080");
				}
				
				@Override
				public String getRequestURI() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getRemoteUser() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getQueryString() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getPathTranslated() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getPathInfo() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Collection<Part> getParts() throws IOException, ServletException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Part getPart(String name) throws IOException, ServletException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getMethod() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public int getIntHeader(String name) {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public Enumeration<String> getHeaders(String name) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Enumeration<String> getHeaderNames() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getHeader(String name) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public long getDateHeader(String name) {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public Cookie[] getCookies() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getContextPath() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getAuthType() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String changeSessionId() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
					// TODO Auto-generated method stub
					return false;
				}
			};
			 res = service.addUser(user, req);
		} catch (Exception e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("200", res.getStatus());
	}
}
