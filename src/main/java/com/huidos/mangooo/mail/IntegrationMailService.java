package com.huidos.mangooo.mail;

/**
 * This class is a  mail service that sends a excel file
 * uses a SMTP protocol
 * you can configure the properties at: mail.properties
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2014/10/22
 * 
 **/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;


@SuppressWarnings("deprecation")
public class IntegrationMailService {

	 private JavaMailSender mailSender;
	 private VelocityEngine velocityEngine;
	 private SimpleMailMessage preConfiguredMessage;
	 	
	 /**
	  * 
	  * @param destination
	  * @param bitacora
	  */
	 public void setModel(String destination,List<?> bitacora){
		 sendConfirmationEmail(destination,bitacora);
	 }
	 
	 /**
	  * sends an email with velociyt also with a attachment excel file
	  * 
	  * @param destination
	  * @param bitacora
	  * @param excelFile
	  */
	 private void sendConfirmationEmail(final String destination,final List<?> bitacora){
		 MimeMessagePreparator preparator = new MimeMessagePreparator() {
	            public void prepare(MimeMessage mimeMessage) throws Exception {
	            	
	            	String [] sendTo = destination.split(",");
	            	
	            	for (int i = 0; i < sendTo.length; i++) {
						String send = sendTo[i];
						
	                MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
	                message.setTo(send);
	                message.setFrom(preConfiguredMessage.getFrom()); // could be parameterized...
	                Map<String, Object> model = new HashMap<String, Object>();
	                model.put("destination", destination);
	                model.put("bitacora", bitacora);
					String text = VelocityEngineUtils.mergeTemplateIntoString(
	                        velocityEngine, "com/huidos/mangooo/mail/mailMangoooReporte.html", model);
	                message.setText(text, true);
	                byte[] bytes =new byte[0];//excelFile.getFileBao().toByteArray();
	                DataSource dataSource = new ByteArrayDataSource(bytes, "application/vnd.ms-excel");
	                message.addAttachment("reporteStats.xls", dataSource);
	            	}
	            }
	        };
	        
	        try {
	        	this.mailSender.send(preparator);
			} catch (MailException e) {
				
			}
	        
	 }
	 
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}


	public void setPreConfiguredMessage(SimpleMailMessage preConfiguredMessage) {
		this.preConfiguredMessage = preConfiguredMessage;
	}
}
