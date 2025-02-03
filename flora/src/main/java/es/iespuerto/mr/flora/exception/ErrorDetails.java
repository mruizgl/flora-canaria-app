package es.iespuerto.mr.flora.exception;

import java.util.Date;

/**
 * The {@code ErrorDetails} class is a simple POJO that encapsulates details about an error.
 * It includes a timestamp indicating when the error occurred, a message describing the error,
 * and additional details about the error.
 * 
 * <p>All fields are immutable and are set through the constructor.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * ErrorDetails errorDetails = new ErrorDetails(new Date(), "Error message", "Error details");
 * }
 * </pre>
 * 
 * <p>Fields:</p>
 * <ul>
 *   <li>{@code timestamp} - The date and time when the error occurred.</li>
 *   <li>{@code message} - A brief message describing the error.</li>
 *   <li>{@code details} - Additional details about the error.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@code getTimestamp()} - Returns the timestamp of the error.</li>
 *   <li>{@code getMessage()} - Returns the message describing the error.</li>
 *   <li>{@code getDetails()} - Returns additional details about the error.</li>
 * </ul>
 */
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    /**
     * Constructs a new {@code ErrorDetails} object with the specified timestamp, message, and details.
     * 
     * @param timestamp The date and time when the error occurred.
     * @param message A brief message describing the error.
     * @param details Additional details about the error.
     */
    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    /**
     * Gets the timestamp of the error.
     * 
     * @return The timestamp when the error occurred.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the message describing the error.
     * 
     * @return The message associated with the error.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets additional details about the error.
     * 
     * @return The additional details of the error.
     */
    public String getDetails() {
        return details;
    }
}
