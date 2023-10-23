package com.github.mjmlmailer.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * Represents an email structure specifically for MJML emails.
 */
@Data
@Builder
public class MjmlEmail {

    private String to;
    private String subject;
    private String templateName;
    private Map<String, Object> params;
}
