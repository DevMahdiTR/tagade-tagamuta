package tagarde.core.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tagarde.core.exceptions.custom.EmailServiceException;
import tagarde.core.utility.ThymeleafUtil;

import java.util.Map;


@Service
@Slf4j
public class EmailServiceImpl implements  EmailService{


    @Value("${spring.mail.username}")
    private String USER_EMAIL;

    private final JavaMailSender mailSender;
    public EmailServiceImpl( JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void sendMail(String to, String subject, String templateName, Map<String, Object> model) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            messageHelper.setFrom(USER_EMAIL);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            String content = ThymeleafUtil.processEmailTemplate(templateName, model);
            messageHelper.setText(content, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed to send email to {}: {}", to, e.getMessage());
            throw new EmailServiceException("Failed to send email to " + to);
        }
    }


}
