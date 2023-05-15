package com.example.mockbasev2.security;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("timestamp", LocalDateTime.now());
            jsonObject.put("message", authException.getMessage());
            jsonObject.put("details", "uri=" + req.getRequestURI().toString());
            res.getWriter().write(jsonObject.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
//        GeneralResponse<?> generalResponse = GeneralResponse.error(HttpStatusConstant.AUTHENTICATION_FAIL_CODE, HttpStatusConstant.AUTHENTICATION_FAIL_MESSAGE);
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String json = ow.writeValueAsString(generalResponse);
//        response.setContentType("application/json");
//        response.setStatus(403); // Bad credentials
//        response.getOutputStream().println(json);
    }
}
