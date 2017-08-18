package com.francetelecom.supervisor.dao.src.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.francetelecom.supervisor.dao.src.interfaces.InterfacePartner;
import com.francetelecom.supervisor.dao.src.model.Link;
import com.francetelecom.supervisor.dao.src.model.Msg;
import com.francetelecom.supervisor.dao.src.model.Partner;
import com.francetelecom.supervisor.dao.src.model.SSLUtil;
import com.francetelecom.supervisor.dao.src.repository.RepositoryHeaders;
import com.francetelecom.supervisor.dao.src.repository.RepositoryPartner;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.mail.MessagingException;
import static java.lang.Math.toIntExact;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class ServicePartnerImpl implements InterfacePartner {
	private static Logger logger = Logger.getLogger(ServicePartnerImpl.class);
	@Autowired
	private RepositoryPartner repositoryPartner;
	@Autowired
	private ServiceParametersImpl serviceParameters;
	@Autowired
	private ServiceSupervisionImpl serviceSupervision;
	@Autowired
	private ServiceMsgImpl serviceMsg;
	@Autowired
	private ServiceLinkImpl serviceLink;
	@Autowired
	private RepositoryHeaders repositoryHeader;

	public List<Partner> getAllparts() {// show all partners
		List<Partner> list = new ArrayList<>();
		repositoryPartner.findAll().forEach(list::add);
		return list;
	}

	public List<Partner> getParts(int user_id) {
		List<Integer> listL = serviceLink.findLinks(user_id);
		List<Partner> listP = new ArrayList<>();
		for (int i = 0; i < listL.size(); i++) {
			listP.add(repositoryPartner.getParts(listL.get(i)));
		}
		return listP;
	}

	public List<Partner> getStats(int id) {// get stats of a partner
		List<Partner> list = getParts(id);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setMax_response_time(serviceSupervision.getMoyenne(list.get(i).getPartner_name()));
			;
			list.get(i).setPartner_id(serviceSupervision.getSuccess(list.get(i).getPartner_name()));
		}
		return list;
	}

	public void addPart(Partner partner) {// add a partner to the table
		repositoryPartner.save(partner);
		Link link = new Link(1, getId(partner.getPartner_name()));
		serviceLink.addLink(link);
		response(partner.getUrl(), partner.getPartner_id());
	}

	public void deletePartnersById(int id) {// delete a partner from the table
		repositoryPartner.deletePartnersById(id);
		serviceLink.deleteLinkByPartnerId(id);
	}

	public void modifyPartner(int id, String name, String url, int max, String mail, int failuremax) {// modify a
																										// partner
		serviceSupervision.modifyPartnerName(repositoryPartner.getName(id), name);
		repositoryPartner.modifyPartner(id, name, url, max, mail, failuremax);
	}

	public String getUrl(int id) {// return an url of a specified id
		return repositoryPartner.getUrl(id);
	}

	public int getId(String name) {// return an id of a specified name
		return repositoryPartner.getId(name);
	}

	public void response(String url, int id) {// calculate the response time of a specified url
		String partner = repositoryPartner.getName(url);
		String parName = serviceParameters.getName(id);
		String parValue = serviceParameters.getValue(id);
		String errormsg;
		if (parName != null && parValue != null)
			url += '?' + parName + '=' + parValue;
		logger.info("id partner =" + id);
		logger.info("connexion attempt at the url" + url);
		try {
			SSLUtil.turnOffSslChecking();
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String date = dateFormat.format(System.currentTimeMillis());
		long startTime = System.currentTimeMillis();
		try {
			RestTemplate template = new RestTemplate();
			HttpEntity<String> entity = new HttpEntity<String>(createHeaders(id));
			logger.info("headers =" + entity);
			ResponseEntity<String> result = template.exchange(url, HttpMethod.GET, entity, String.class);
			long temps = System.currentTimeMillis() - startTime;
			HttpStatus statusCode = result.getStatusCode();
			logger.info("http status =" + statusCode);
			logger.info("response time =" + temps);
			serviceSupervision.addService(partner, date, statusCode.toString(), toIntExact(temps));
			if (temps > repositoryPartner.getMaxTime(id)) {
				int failure_remaining = repositoryPartner.getFailure_remaining(id) - 1;
				logger.warn("alerte");
				logger.warn("failurer remaining =" + failure_remaining);
				repositoryPartner.modifyFailure_remaining(id, failure_remaining);
				if (failure_remaining <= 0) {
					errormsg = "alarme déclaré le " + date + "\n" + "chez le partenaire " + partner + "\n";
					serviceSupervision.sendMail(repositoryPartner.getEmail(id), "testmail", errormsg);
					Msg msg = new Msg("Alarme chez le partenaire " + partner, date, id);
					serviceMsg.addMsg(msg);
				}
				if(failure_remaining<=0) {
					repositoryPartner.modifyFailure_remaining(id, repositoryPartner.getFailuremax(id));
				}
			}
		} catch (final HttpClientErrorException e) {
			long temps = System.currentTimeMillis() - startTime;
			HttpStatus statusCode = e.getStatusCode();
			logger.info("http status =" + statusCode);
			serviceSupervision.addService(partner, date, statusCode.toString(), toIntExact(temps));
			if (temps > repositoryPartner.getMaxTime(id)) {
				int failure_remaining = repositoryPartner.getFailure_remaining(id) - 1;
				logger.info("alerte");
				logger.info("failurer remaining =" + failure_remaining);
				repositoryPartner.modifyFailure_remaining(id, failure_remaining);
				if (failure_remaining == 0) {
					errormsg = "alarme déclaré le " + date + "\n" + "chez le partenaire " + partner + "\n";
					try {
						serviceSupervision.sendMail(repositoryPartner.getEmail(id), "testmail", errormsg);
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					repositoryPartner.modifyFailure_remaining(id, repositoryPartner.getFailuremax(id));
					Msg msg = new Msg("Alarme chez le partenaire " + partner, date, id);
					serviceMsg.addMsg(msg);
				}
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	HttpHeaders createHeaders(int id) {// return header(user+password) encoded witt base64
		HttpHeaders header = new HttpHeaders();
		String name = repositoryHeader.getName(id);
		String password = repositoryHeader.getPassword(id);

		String auth = name + ":" + password;
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(Charset.forName("US-ASCII")));
		String base64Creds = "Basic " + new String(encodedAuth);
		header.add("Authorization", base64Creds);

		return header;
	}

	public void responseall() {// calculate response time of all partners
		List<Partner> liste = getAllparts();
		for (int i = 0; i < liste.size(); i++) {
			response(liste.get(i).getUrl(), liste.get(i).getPartner_id());
		}
	}
}