package com.nnk.springboot.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordCheckServiceImpl implements PasswordCheckService {

    public boolean passwordCheck(String pw) {
        int len = pw.length(); //valid password and invalid passwords (missing symbol, missing letter, faulty length, surpassing length*)

        boolean digit = false;
        boolean letterUpperCase = false;
        boolean symbol = false;

        if (pw.length() > 7) {// length is OK
            char[] pwdArray = pw.toCharArray();
            for (int i = 0; i < len; i++) {
                if (Character.isLetterOrDigit(pwdArray[i])){
                    if (Character.isDigit(pwdArray[i])) {
                        digit = true;
                    }
                    else {
                        if ((!Character.isLowerCase(pwdArray[i]))) letterUpperCase = true;
                    }
                }
                else {
                    symbol = true;
                }
            }
        } else {
            return false;
        }
        return (digit) && (symbol) && (letterUpperCase);
    }
}
