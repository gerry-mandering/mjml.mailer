package com.github.mjmlmailer.service;

import com.github.mjmlconverter.builder.MjmlRequestBuilder;
import com.github.mjmlconverter.converter.MjmlConverter;
import com.github.mjmlconverter.dto.HtmlResponse;
import com.github.mjmlconverter.dto.MjmlRequest;
import com.github.mjmlmailer.dto.MjmlEmail;
import com.github.mjmlmailer.exception.EmailSendingException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Service implementation of the MjmlEmailService interface, responsible for sending MJML-based emails.
 */
@Service
@RequiredArgsConstructor
public class MjmlEmailServiceImpl implements MjmlEmailService {

    private final MjmlRequestBuilder mjmlRequestBuilder;
    private final MjmlConverter mjmlConverter;
    private final JavaMailSender mailSender;

    /**
     * Sends an MJML email by converting MJML content to HTML and then sending the email.
     *
     * @param mjmlEmail Contains the details of the MJML email, such as recipient, subject, template name, and parameters.
     * @throws IOException If there's an issue with input/output operations.
     * @throws MessagingException If there's an issue with constructing or sending the email message.
     */
    @Override
    public void send(MjmlEmail mjmlEmail) throws IOException, MessagingException {
        try {
            MjmlRequest mjmlRequest = mjmlRequestBuilder.build(mjmlEmail.getTemplateName(), mjmlEmail.getParams());
            HtmlResponse htmlResponse = mjmlConverter.convert(mjmlRequest);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(mjmlEmail.getTo());
            helper.setSubject(mjmlEmail.getSubject());
            helper.setText(htmlResponse.getHtml(), true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailSendingException("Failed to send email.", e);
        }
    }
}
