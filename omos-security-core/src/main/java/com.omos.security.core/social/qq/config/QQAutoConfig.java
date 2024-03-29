///**
// *
// */
//package com.omos.security.core.social.qq.config;
//
//import com.omos.security.core.properties.QQProperties;
//import com.omos.security.core.properties.SecurityProperties;
//import com.omos.security.core.social.qq.connet.QQConnectionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.social.connect.ConnectionFactory;
//
///**
// * @author zhailiang
// *
// */
//@Configuration
//@ConditionalOnProperty(prefix = "omos.security.social.qq", name = "app-id")
//public class QQAutoConfig extends SocialAutoConfigurerAdapter {
//
//	@Autowired
//	private SecurityProperties securityProperties;
//
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see
//	 * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
//	 * #createConnectionFactory()
//	 */
//	@Override
//	protected ConnectionFactory<?> createConnectionFactory() {
//		QQProperties qqConfig = securityProperties.getSocial().getQq();
//		return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
//	}
//
//
//}
