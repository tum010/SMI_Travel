package com.smi.travel.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

public class SMIBeanNameGenerator implements BeanNameGenerator {

	private  Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {
		logger.debug("SMIBeanNameGenerator : "+beanDefinition.getBeanClassName()+"  ---->  "+beanDefinition.getBeanClassName().replaceAll("Impl", ""));
		return beanDefinition.getBeanClassName().replaceAll("Impl", "");
	}

}
