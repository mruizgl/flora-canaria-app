package es.iespuerto.mr.flora.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * GlobalExceptionHandler is a centralized exception handling class that handles exceptions
 * thrown by the application and provides appropriate HTTP responses.
 * 
 * <p>This class extends {@link ResponseEntityExceptionHandler} to leverage the default Spring Boot exception handling.
 * It uses {@link ControllerAdvice} to allow global exception handling across the entire application.</p>
 * 
 * <p>The class contains two exception handler methods:</p>
 * <ul>
 *   <li>{@link #resourceNotFoundException(ResourceNotFoundException, WebRequest)} - Handles {@link ResourceNotFoundException}
 *       and returns a 404 NOT FOUND response with error details.</li>
 *   <li>{@link #globleExcpetionHandler(Exception, WebRequest)} - Handles all other exceptions and returns a 500
 *       INTERNAL SERVER ERROR response with error details.</li>
 * </ul>
 * 
 * <p>Each handler method constructs an {@link ErrorDetails} object containing the timestamp, error message, 
 * and request description, which is then returned in the {@link ResponseEntity}.</p>
 * 
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@link ControllerAdvice} - Specifies that this class provides global exception handling across the application.</li>
 *   <li>{@link ExceptionHandler} - Marks methods as handlers for specific exceptions.</li>
 * </ul>
 * 
 * @see ResourceNotFoundException
 * @see Exception
 * @see ErrorDetails
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles {@link ResourceNotFoundException} and returns a 404 NOT FOUND response with error details.
     *
     * @param ex the exception thrown
     * @param request the web request
     * @return a {@link ResponseEntity} containing the error details and HTTP status NOT FOUND (404)
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles all other exceptions and returns a 500 INTERNAL SERVER ERROR response with error details.
     *
     * @param ex the exception thrown
     * @param request the web request
     * @return a {@link ResponseEntity} containing the error details and HTTP status INTERNAL SERVER ERROR (500)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
