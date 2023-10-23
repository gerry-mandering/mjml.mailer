package com.github.mjmlmailer.service;

import com.github.mjmlmailer.dto.MjmlEmail;
import jakarta.mail.MessagingException;

import java.io.IOException;

/**
 * Interface defining the contract for MJML email-related services.
 */
public interface MjmlEmailService {

    /**
     * Sends an MJML email by converting MJML content to HTML and then sending the email.
     *
     * @param mjmlEmail Contains the details of the MJML email, such as recipient, subject, template name, and parameters.
     * @throws IOException If there's an issue with input/output operations.
     * @throws MessagingException If there's an issue with constructing or sending the email message.
     */
    void send(MjmlEmail mjmlEmail) throws IOException, MessagingException;
}
