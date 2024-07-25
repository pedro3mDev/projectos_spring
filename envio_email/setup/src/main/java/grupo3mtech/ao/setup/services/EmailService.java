package grupo3mtech.ao.setup.services;

import grupo3mtech.ao.setup.enums.StatusEmail;
import grupo3mtech.ao.setup.models.EmailModel;
import grupo3mtech.ao.setup.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;

public class EmailService {
    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailfrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailModel.getStatusEmail();
        }catch (MailException e){
            emailModel.getStatusEmail();
        }finally {
            return emailRepository.save(emailModel);
        }
    }
}
