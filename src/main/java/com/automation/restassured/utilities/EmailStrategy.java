package com.automation.restassured.utilities;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.UUID;

import uk.co.jemos.podam.common.AttributeStrategy;

public class EmailStrategy implements AttributeStrategy<String>{

	@Override
	public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {

		return String.format("%s@%s", UUID.randomUUID().toString().substring(0, 8), "gmail.com");

	}

}
