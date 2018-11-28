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
            + "<h1>Mensaje de Evaluación</h1><br>\n"
            + "<h3>Estimado : _nombre_</h3>\n"
            + "<p>Gracias por responder la evaluacion.</p>\n"
            + "<p>Debes esperar a que tu contraparte responda la evaluacion que queda para obtnener tu nota final.</p>\n"
            + "<p>Agencia Investigadores wfbs</p>\n"
            + "</div>";

    private String bodyEvaluacionFinal = "<div style='text-align:center'>\n"
            + "<h1>Mensaje de Evaluación</h1><br>\n"
            + "<h3>Estimado :_nombre_</h3>\n"
            + "<p>Gracias por responder la evaluacion.</p>\n"
            + "<p>tu contraparte ha respondido la evaluacion pendiente.</p>\n"
            + "<p>por lo tanto podemos obtener tu nota.</p>\n"
            + "<p>La nota obtnenida en la evaluacion número _numero_ es: _nota_ .</p>\n"
            + "<p>Si tienes dudas favor acercarte a tu jefe.</p>\n"
            + "<p>Agencia Investigadores wfbs</p>\n"
            + "</div>";

    private String bodyConsultarNotaOK = "<div style='text-align:center'>\n"
            + "<h1>Mensaje de Evaluación</h1><br>\n"
            + "<h3>Estimado :_nombre_</h3>\n"
            + "<p>Gracias por consultar por tu nota.</p>\n"
            + "<p>La persona evaluada en este cuestionario fue: _persona_ .</p>\n"
            + "<p>La nota obtnenida en la evaluación número _numero_ es: _nota_ .</p>\n"
            + "<p>Como observación has obtenido lo siguiente:</p>\n"
            + "<p>_observacion_</p>\n"
            + "<p>Agencia Investigadores wfbs</p>\n"
            + "</div>";

    private String bodyConsultarNotaNoOK = "<div style='text-align:center'>\n"
            + "<h1>Mensaje de Evaluación</h1><br>\n"
            + "<h3>Estimado :_nombre_</h3>\n"
            + "<p>Gracias por consultar por tu nota.</p>\n"
            + "<p>Tu contraparte aún no responde la Evaluacion.</p>\n"
            + "<p>Debes esperar a que finalice la evaluación, intenta más tarde.</p>\n"
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
        //    session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
        //    session.getProperties().put("mail.smtp.starttls.enable", "true");
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

    public void enviarMailEvaluacionFinal(Persona cliente, String nota, String idEvaluacion) {
        init();
        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(cliente.getEmailPersona()));
            message.setSubject("Evaluaciones WFBS");
            bodyEvaluacionFinal = bodyEvaluacionFinal.replace("_nombre_", cliente.getNombrePersona() + " " + cliente.getApellidoPaterno());
            bodyEvaluacionFinal = bodyEvaluacionFinal.replace("_numero_", idEvaluacion);
            bodyEvaluacionFinal = bodyEvaluacionFinal.replace("_nota_", nota);
            message.setContent(bodyEvaluacionFinal, "text/html; charset=utf-8");
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

    
     public void enviarNotaOk(Persona cliente, String nota, String idEvaluacion,String observacion,String personaEval) {
        init();
        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(cliente.getEmailPersona()));
            message.setSubject("Evaluaciones WFBS");
            bodyConsultarNotaOK = bodyConsultarNotaOK.replace("_nombre_", cliente.getNombrePersona() + " " + cliente.getApellidoPaterno());
            bodyConsultarNotaOK = bodyConsultarNotaOK.replace("_numero_", idEvaluacion);
            bodyConsultarNotaOK = bodyConsultarNotaOK.replace("_nota_", nota);
            bodyConsultarNotaOK = bodyConsultarNotaOK.replace("_persona_", personaEval);
            bodyConsultarNotaOK = bodyConsultarNotaOK.replace("_observacion_", observacion);
            message.setContent(bodyConsultarNotaOK, "text/html; charset=utf-8");
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
     
     
     
        public void enviarNotaNoOk(Persona cliente, String nota, String idEvaluacion,String observacion,String personaEval) {
        init();
        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(cliente.getEmailPersona()));
            message.setSubject("Evaluaciones WFBS");
            bodyConsultarNotaNoOK = bodyConsultarNotaNoOK.replace("_nombre_", cliente.getNombrePersona() + " " + cliente.getApellidoPaterno());
            bodyConsultarNotaNoOK = bodyConsultarNotaNoOK.replace("_numero_", idEvaluacion);
            bodyConsultarNotaNoOK = bodyConsultarNotaNoOK.replace("_nota_", nota);
            bodyConsultarNotaNoOK = bodyConsultarNotaNoOK.replace("_persona_", personaEval);
            bodyConsultarNotaNoOK = bodyConsultarNotaNoOK.replace("_observacion_", observacion);
            message.setContent(bodyConsultarNotaNoOK, "text/html; charset=utf-8");
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
