//package ua.kislov.shop_back.exception_handlers;
//
//import org.hibernate.HibernateException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import ua.kislov.shop_back.exception.UserNotFoundException;
//
//import java.util.Arrays;
//
//@RestControllerAdvice
//public class ExceptionHandlerAdvice {
//
//    @ExceptionHandler
//    public ResponseEntity<String> userNotFound(UserNotFoundException e){
//        Arrays.stream(e.getStackTrace()).forEach(System.out::println);
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(404));
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<String> dbOrmJpaException(HibernateException e){
//        System.out.println(Arrays.toString(e.fillInStackTrace().getStackTrace()));
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
