package com.codetech.oauth2.validator;
import com.codetech.oauth2.model.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserModel.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        UserModel user = (UserModel) object;

        if(user.getPassword().length() <3){
            errors.rejectValue("password","Length", "Password must be at least 3 characters");
        }

        if(!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("confirmPassword","Match", "Passwords must match");

        }

        //confirmPassword



    }
}
