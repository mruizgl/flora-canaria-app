package es.iespuerto.mr.flora.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested resource is not found.
 * This exception is mapped to a 404 Not Found HTTP status code.
 * 
 * When this exception is thrown, it triggers an HTTP response with a 404 status code,
 * indicating that the resource could not be located.
 * 
 * Annotations:
 * <ul>
 *   <li>{@link ResponseStatus}: Marks the exception to be associated with the HTTP status code 
 *       {@link HttpStatus#NOT_FOUND} (404), which is used when a resource is not found.</li>
 * </ul>
 * 
 * Constructor:
 * <ul>
 *   <li>{@link #ResourceNotFoundException(String)}: Constructs a new {@link ResourceNotFoundException} 
 *       with the specified detail message.</li>
 * </ul>
 * 
 * @see HttpStatus#NOT_FOUND
 * @see Exception
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new {@link ResourceNotFoundException} with the specified detail message.
     * 
     * @param message The detail message describing the exception.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
