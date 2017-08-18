package com.francetelecom.supervisor.dao.src.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.francetelecom.supervisor.dao.src.interfaces.InterfaceSupervision;
import com.francetelecom.supervisor.dao.src.model.Supervision;
import com.francetelecom.supervisor.dao.src.repository.RepositorySupervision;

@Service
public class ServiceSupervisionImpl implements InterfaceSupervision {
	@Autowired
	private RepositorySupervision repositorySupervision;
	@Autowired
	private JavaMailSender javaMailSender;

	public void addService(String partner, String date, String response, int response_time) {// add an element to the
																								// table config
		Supervision a = new Supervision(partner, date, response, response_time);
		repositorySupervision.save(a);
		System.out.println(repositorySupervision.getDate());
	}

	public List<Supervision> getStats(String name) {// get stats
		return repositorySupervision.getStats(name);
	}

	public int getMoyenne(String name) {// get average response time
		return repositorySupervision.getMoyenne(name);
	}

	public int getSuccess(String name) {// get pourcentage of successful http requests
		return repositorySupervision.getSuccess(name) * 100 / repositorySupervision.getTotal(name);
	}

	public void modifyPartnerName(String oldname, String newname) {// modify partner name
		repositorySupervision.modifyPartnerName(oldname, newname);
	}

	public void sendMail(String to, String subject, String body) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);// true indicates multipart message
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true);// true indicates body is html
		javaMailSender.send(message);
	}

	public List<Integer> getCurveTimes(String partner_name) {// get response times of a partner today
		List<Integer> myList = repositorySupervision.getCurveTimes(partner_name);
		int l = 100;
		if (myList.size() < 100)
			l = myList.size();
		myList = myList.subList(myList.size() - l, myList.size());
		return myList;
	}

	public List<String> getCurveDates(String partner_name) {// get dates of thread requests today
		List<String> myList = repositorySupervision.getCurveDates(partner_name);
		int l = 100;
		if (myList.size() < 100)
			l = myList.size();
		myList = myList.subList(myList.size() - l, myList.size());
		return myList;
	}
	public  void write(String thread){
		
		try {
			OutputStream output = new FileOutputStream("src\\main\\resources\\application.properties");
			Properties prop = new Properties();
			prop.setProperty("spring.datasource.url", "jdbc:mysql://localhost:3306/Sofrecom");
			prop.setProperty("spring.datasource.driver-class-name", "com.mysql.jdbc.Driver");
			prop.setProperty("spring.datasource.username", "root");
			prop.setProperty("spring.datasource.password", "Sofrecom123#");
			prop.setProperty("spring.jpa.show-sql", "true");
			prop.setProperty("spring.jpa.hibernate.ddl-auto", "update");
			prop.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
			prop.setProperty("logging.level.org.springframework.web", "DEBUG");
			prop.setProperty("logging.level.org.hibernate", "ERROR");
			prop.setProperty("logging.file", "myapplication.log");
			prop.setProperty("spring.mail.properties.mail.smtp.connecttimeout", "5000");
			prop.setProperty("spring.mail.properties.mail.smtp.timeout", "3000");
			prop.setProperty("spring.mail.properties.mail.smtp.writetimeout", "5000");
			prop.setProperty("spring.mail.host", "smtp.gmail.com");
			prop.setProperty("spring.mail.password", "Sofrecom123#");
			prop.setProperty("spring.mail.port", "587");
			prop.setProperty("spring.mail.username", "kais.sofrecon@gmail.com");
			prop.setProperty("spring.mail.properties.mail.smtp.starttls.enable", "true");
			prop.setProperty("security.require-ssl", "true");
			prop.setProperty("spring.mail.properties.mail.smpt.auth", "true");
			prop.setProperty("fixedDelay.in.milliseconds",thread);


			prop.store(output, null);
			output.close();

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			
			}
		
		}
	public int getThread() throws IOException {
		Properties prop = new Properties();
    	InputStream input = null;

    	try {

    		input = new FileInputStream("src\\main\\resources\\application.properties");

    		// load a properties file
    		prop.load(input);

    		// get the property value and print it out
    		return Integer.parseInt(prop.getProperty("fixedDelay.in.milliseconds"))/1000;

    	}  finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}

      }
	}
