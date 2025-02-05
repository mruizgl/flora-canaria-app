package es.iespuerto.mr.flora.exception;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorDetailsTest {

    @Test
    void constructorInitializesFieldsCorrectly() {
        Date timestamp = new Date();
        String message = "Error message";
        String details = "Error details";

        ErrorDetails errorDetails = new ErrorDetails(timestamp, message, details);

        assertEquals(timestamp, errorDetails.getTimestamp());
        assertEquals(message, errorDetails.getMessage());
        assertEquals(details, errorDetails.getDetails());
    }

    @Test
    void getTimestampReturnsCorrectTimestamp() {
        Date timestamp = new Date();
        ErrorDetails errorDetails = new ErrorDetails(timestamp, "Error message", "Error details");

        assertEquals(timestamp, errorDetails.getTimestamp());
    }

    @Test
    void getMessageReturnsCorrectMessage() {
        String message = "Error message";
        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, "Error details");

        assertEquals(message, errorDetails.getMessage());
    }

    @Test
    void getDetailsReturnsCorrectDetails() {
        String details = "Error details";
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Error message", details);

        assertEquals(details, errorDetails.getDetails());
    }

    @Test
    void constructorHandlesNullValues() {
        ErrorDetails errorDetails = new ErrorDetails(null, null, null);

        assertNull(errorDetails.getTimestamp());
        assertNull(errorDetails.getMessage());
        assertNull(errorDetails.getDetails());
    }
}