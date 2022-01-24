package pl.coderslab.charity.registration;

import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.UserService;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final UserService service;
    private final JavaMailSender mailSender;
    private final MessageSource messages;

    public RegistrationListener(UserService service, JavaMailSender mailSender, MessageSource messages) {
        this.service = service;
        this.mailSender = mailSender;
        this.messages = messages;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        service.createVerificationTokenForUser(user, token);

        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        mailSender.send(email);
    }

    private SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final User user, final String token) {
        final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl
                = event.getAppUrl() + "/registration/confirm?token=" + token;
        final String message = messages.getMessage("message.registrationsSuccessLink", null, event.getLocale());

        final SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("noreplay@devnull.gdn");
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);

        return email;
    }


}
