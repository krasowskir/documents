package com.example.documents.exceptionhandler;

import com.example.documents.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {

        LOGGER.info("---onAuthentication aufgerufen---");
        httpServletResponse.setStatus(FORBIDDEN.value());
        httpServletResponse.getOutputStream().println("Pech gehabt");
    }
}
