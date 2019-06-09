/**
 * 
 */
package com.omos.security.core.validate;

import com.omos.security.core.properties.SecurityProperties;
import com.omos.security.core.validate.image.ImageCodeGenerator;
import com.omos.security.core.validate.sms.DefaultSmsCodeSender;
import com.omos.security.core.validate.sms.SmsCodeSender;
import com.omos.security.core.properties.SecurityProperties;
import com.omos.security.core.validate.image.ImageCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhailiang
 *
 */
@Configuration
public class ValidateCodeBeanConfig {
	
//	@Autowired
//	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		SecurityProperties securityProperties = new SecurityProperties();
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}

}
