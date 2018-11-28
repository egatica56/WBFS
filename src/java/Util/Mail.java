/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Entities.Persona;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Testing
 */
public class Mail {

    private final Properties properties = new Properties();

    private final String PASSWORD = "portafolio2018";
    private String bodyEvaluacionRespondida = "<div style='text-align:center'>\n"
            + "<h1>Mensaje de Evaluacion</h1><br>\n"
            + "<h3>Estimado : _nombre_</h3>\n"
            + "<p>Gracias por responder la evaluacion.</p>\n"
            + "<p>Debes esperar a que tu contraparte responda la evaluacion que queda para obtnener tu nota final.</p>\n"
            + "<p>Agencia Investigadores wfbs</p>\n"
            + "</div>";

    private Session session;

    private void init() {
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.mail.sender", "investigaciones.wfbs@gmail.com");
        properties.put("mail.smtp.user", "investigaciones.wfbs@gmail.com");


        


        session = Session.getDefaultInstance(properties);
        session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
        session.getProperties().put("mail.smtp.starttls.enable", "true");
    }

    public void enviarMailEvaluacion(Persona cliente) {
        init();
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(cliente.getEmailPersona()));
            message.setSubject("Evaluaciones WFBS");
            bodyEvaluacionRespondida = bodyEvaluacionRespondida.replace("_nombre_", cliente.getNombrePersona() + " " + cliente.getApellidoPaterno());
            message.setContent(bodyEvaluacionRespondida, "text/html; charset=utf-8");
            Transport t = session.getTransport("smtp");
            t.connect((String) properties.get("mail.smtp.user"), PASSWORD);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException me) {
            System.out.println(me.getMessage());
            //Aqui se deberia o mostrar un mensaje de error o en lugar
            //de no hacer nada con la excepcion, lanzarla para que el modulo
            //superior la capture y avise al usuario con un popup, por ejemplo.
            return;
        }
    }

}
