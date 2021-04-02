package com.practicaweb.practicadaw.auxClasses;

import com.practicaweb.practicadaw.model.Criptocurrency;

import java.util.List;

public class AuxUser {
    public static boolean verificationEmail (String email, String confirmEmail){
        return email.equals(confirmEmail);
    }

    public static boolean verificationPassword (String password, String confirmPassword){
        return password.equals(confirmPassword);
    }

}
