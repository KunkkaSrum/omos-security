/**
 * 
 */
package com.omos.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author zhailiang
 *
 */
public interface ValidateCodeGenerator {

	ValidateCode generate(ServletWebRequest request);
	
}
