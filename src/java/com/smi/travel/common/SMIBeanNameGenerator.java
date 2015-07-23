package com.smi.travel.common;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

public class SMIBeanNameGenerator implements BeanNameGenerator {

	@Override
	public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {
		return beanDefinition.getBeanClassName().replaceAll("Impl", "");
	}

}
