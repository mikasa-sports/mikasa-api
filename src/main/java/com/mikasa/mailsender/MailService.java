package com.mikasa.mailsender;

import com.mikasa.mailsender.config.parameters.EmailProperties;
import com.mikasa.dto.user.UserResponseDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {

  public static final String FIRSTNAME = "firstname";
  public static final String CONFIRMATION_URL = "confirmationUrl";
  public static final String CONFIRM_EMAIL_TEMPLATE = "confirm-email";
  public static final String VERIFY_USER_TEMPLATE = "verify-user.html";
  private static final Logger log = LoggerFactory.getLogger(MailService.class);

  private final JavaMailSender javaMailSender;
  private final EmailProperties emailProperties;
  private final TemplateEngine templateEngine;

  public MailService(JavaMailSender javaMailSender, EmailProperties emailProperties,
      TemplateEngine templateEngine) {
    this.javaMailSender = javaMailSender;
    this.emailProperties = emailProperties;
    this.templateEngine = templateEngine;
  }

  @Async
  public void sendEmail(UserResponseDto user) {
    try {
      log.info("Sending email notification to verify the user: {}", user.getEmail());
      String userEmail = user.getEmail();

      MimeMessage mail = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mail, true);

      helper.setFrom(user.getName());
      helper.setTo(userEmail);
      helper.setSubject("Confirm Your Email");
      helper.setText(generateEmailContent(user.getName(),
          String.format("%s/%s?token=%s", emailProperties.getConfirmEmailPath(),
              VERIFY_USER_TEMPLATE, userEmail)), true);

      javaMailSender.send(mail);
    } catch (MessagingException e) {
      log.error(e.getMessage());
    }
  }

  private String generateEmailContent(String firstname, String confirmationUrl) {
    Context context = new Context();
    context.setVariable(FIRSTNAME, firstname);
    context.setVariable(CONFIRMATION_URL, confirmationUrl);

    return templateEngine.process(CONFIRM_EMAIL_TEMPLATE, context);
  }
}
