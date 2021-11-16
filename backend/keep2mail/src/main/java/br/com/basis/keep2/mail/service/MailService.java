package br.com.basis.keep2.mail.service;

import br.com.basis.keep2.mail.service.dto.EmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    @Bean
    public Consumer<EmailDTO> mailConsumer() {
        return this::enviarEmail;
    }

    private void enviarEmail(EmailDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@keep2.com.br");
        message.setTo(emailDTO.getDestinatario());
        message.setSubject(emailDTO.getAssunto());
        message.setText(emailDTO.getConteudo());
        javaMailSender.send(message);
    }

}
