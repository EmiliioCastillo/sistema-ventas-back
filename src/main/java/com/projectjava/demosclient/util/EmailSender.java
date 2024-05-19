package com.projectjava.demosclient.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class EmailSender {


    @Autowired
    private JavaMailSender javaMailSender;

    public void setPasswordEmail(String email, String token) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage);

        String enlaceRecuperacion = "http://localhost:5173/token";
        mimeHelper.setTo(email);
        mimeHelper.setSubject("Recuperación de contraseña");
        mimeHelper.setText("<div>" +
                "<h2>Hola usuario </h2>" +
                "<span>Este es su token: </span>"
                + token +
                "<a href=\"" + enlaceRecuperacion + "\" target=\"_blank\">" +
                " Haga clic en este enlace para recuperar su contraseña" +
                "</a>"
                 +
                "<span> Si ud no solicito a nadie este email, porfavor ignorelo.</span>" +
                "</div>", true);
        javaMailSender.send(mimeMessage);
    }

}
