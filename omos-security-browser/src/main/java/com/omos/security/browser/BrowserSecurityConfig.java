/**
 * 
 */
package com.omos.security.browser;

import com.omos.security.browser.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.omos.security.core.authentication.AbstractChannelSecurityConfig;
import com.omos.security.core.properties.SecurityConstants;
import com.omos.security.core.properties.SecurityProperties;
import com.omos.security.core.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author zhailiang
 *
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
//
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

	@Autowired
	private SpringSocialConfigurer imoocSocialSecurityConfig;
//
//	@Autowired
//	private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
//
//	@Autowired
//	private InvalidSessionStrategy invalidSessionStrategy;

//	@Autowired
//	protected AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
//
//	@Autowired
//	protected AuthenticationFailureHandler imoocAuthenticationFailureHandler;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("123");
		applyPasswordAuthenticationConfig(http);

		http
				.apply(validateCodeSecurityConfig)
				.and()
				.apply(smsCodeAuthenticationSecurityConfig)
				.and()
				.apply(imoocSocialSecurityConfig)
				.and()
				.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
//				.and()
//				.sessionManagement()
//				.invalidSessionStrategy(invalidSessionStrategy)
//				.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
//				.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
//				.expiredSessionStrategy(sessionInformationExpiredStrategy)
//				.and()
				.and()
				.authorizeRequests()
				.antMatchers(
						SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
						SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
						securityProperties.getBrowser().getLoginPage(),
						SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
						securityProperties.getBrowser().getSignUpUrl(),
						"*.json",
						"/imooc-signIn.html",
						"/user/regist")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.csrf().disable();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}

}