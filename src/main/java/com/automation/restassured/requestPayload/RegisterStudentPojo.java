package com.automation.restassured.requestPayload;

import com.automation.restassured.utilities.EmailStrategy;
import com.automation.restassured.utilities.PhoneNumberStrategy;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Getter
@Setter
public class RegisterStudentPojo {
	    public String name;
	    
	    @PodamStrategyValue(EmailStrategy.class)
	    public String email;
	    @PodamStrategyValue(PhoneNumberStrategy.class)
	    public String phone;
	    
	    public String userName;
	    public String password;
}
