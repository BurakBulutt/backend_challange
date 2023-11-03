package com.example.example_6.exception;

/**
 * Servicelerde kullanilan temel exception sinifi.NullPointerException'dan turemistir.
 */
public class NotFoundException extends NullPointerException {
    public NotFoundException(String message) {
        super(message);
    }
}
