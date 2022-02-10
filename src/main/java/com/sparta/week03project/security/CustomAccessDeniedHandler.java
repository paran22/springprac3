//package com.sparta.week03project.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.sparta.week03project.exception.ErrorCode.NOT_ALLOWED;
//
//
//// 500 에러 뜸
//@Component
//public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void handle(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            AccessDeniedException accessDeniedException)
//            throws IOException, ServletException {
//
//        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        Map<String, Object> data = new HashMap<>();
//        data.put("errorCode", NOT_ALLOWED.getErrorCode());
//        data.put("errorMessage", NOT_ALLOWED.getErrorMessage());
//        data.put("httpStatus", NOT_ALLOWED.getHttpStatus());
//
//        response.getOutputStream().println(objectMapper.writeValueAsString(data));
//    }
//}
