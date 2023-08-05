package com.dhanush.paymentservices.exception;


import com.dhanush.paymentservices.modal.ErrorBlock;
import com.dhanush.paymentservices.modal.PaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
/*
@ExceptionHandler(WebExchangeBindException.class)
public ResponseEntity<String> handleRequestBodyError(WebExchangeBindException ex){
    log.error("Exception Caught in handleRequestBodyError : {}",ex.getMessage(),ex);
    var error= ex.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .sorted()
            .collect(Collectors.joining(","));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
}
*/
@ExceptionHandler(PaymentException.class)
    public ResponseEntity<PaymentResponse> handleInventoryCheckException(PaymentException ex){
        ErrorBlock error=new ErrorBlock(ex.getMessage(), "1001");
    PaymentResponse paymentResponse=new PaymentResponse();
    paymentResponse.setError(error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(paymentResponse);

    }

}
