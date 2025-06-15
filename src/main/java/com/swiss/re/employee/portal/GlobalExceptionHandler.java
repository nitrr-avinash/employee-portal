//package com.swiss.re.employee.portal;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("error", "Internal Server Error");
//        body.put("message", ex.getMessage()); // or a generic message
//        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
