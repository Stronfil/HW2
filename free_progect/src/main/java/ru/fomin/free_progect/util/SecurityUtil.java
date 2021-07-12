package ru.fomin.free_progect.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static String getEmail(){
       return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
