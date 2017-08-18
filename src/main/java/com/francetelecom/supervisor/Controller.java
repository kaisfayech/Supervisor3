package com.francetelecom.supervisor;

import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.francetelecom.supervisor.dao.src.model.Headers;
import com.francetelecom.supervisor.dao.src.model.Link;
import com.francetelecom.supervisor.dao.src.model.Msg;
import com.francetelecom.supervisor.dao.src.model.Partner;
import com.francetelecom.supervisor.dao.src.model.Supervision;
import com.francetelecom.supervisor.dao.src.model.User;
import com.francetelecom.supervisor.dao.src.service.ServiceHeadersImpl;
import com.francetelecom.supervisor.dao.src.service.ServiceLinkImpl;
import com.francetelecom.supervisor.dao.src.service.ServiceMsgImpl;
import com.francetelecom.supervisor.dao.src.service.ServicePartnerImpl;
import com.francetelecom.supervisor.dao.src.service.ServiceSupervisionImpl;
import com.francetelecom.supervisor.dao.src.service.ServiceUserImpl;

@CrossOrigin(origins = "http://localhost:4200")
@EnableScheduling
@RestController
public class Controller {
	@Autowired
	private ServicePartnerImpl servicePartner;
	@Autowired
	private ServiceHeadersImpl serviceHeader;
	@Autowired
	private ServiceUserImpl serviceUser;
	@Autowired
	private ServiceLinkImpl serviceLink;
	@Autowired
	private ServiceSupervisionImpl serviceSupervision;
	@Autowired
	private ServiceMsgImpl ServiceMsg;

	@RequestMapping("/partners/response/{id}")
	public void response(@PathVariable int id) {// calculate response time of a specified id
		String url = servicePartner.getUrl(id);
		servicePartner.response(url, id);
	}

	@Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
	@RequestMapping("/partners/all") //// calculate response time of all partners
	public void responseall() {
		servicePartner.responseall();

	}

	@RequestMapping("/partners/showall") // get all partners
	public List<Partner> getAllParts() {
		return servicePartner.getAllparts();
	}

	@RequestMapping("/partners/show/{id}") // get partners visible for a specific user
	public List<Partner> getParts(@PathVariable int id) {
		return servicePartner.getParts(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/partners/add") // add a partner
	public void addPart(@RequestBody Partner partner) {
		servicePartner.addPart(partner);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/partners/delete/{id}") // delete a partner
	public void deletePart(@PathVariable int id) {
		servicePartner.deletePartnersById(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/partners/modify") // modify a partner
	public void modifyPartner(@RequestBody Partner partner) {
		servicePartner.modifyPartner(partner.getPartner_id(), partner.getPartner_name(), partner.getUrl(),
				partner.getMax_response_time(), partner.getMail(), partner.getFailuremax());
	}

	@RequestMapping(value = "/users/showall") // get all users expect admin
	public List<User> getUser() {
		return serviceUser.getAllUsers();
	}

	@RequestMapping(value = "/login") // get all users
	public List<User> login() {
		return serviceUser.login();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users/delete/{id}") // delete an user
	public void deleteUser(@PathVariable int id) {
		serviceUser.deleteUserById(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users/add") // add an user
	public void addUser(@RequestBody User user) {
		serviceUser.addUser(user);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users/modify") // modify an user
	public void modifyUser(@RequestBody User user) {
		serviceUser.modifyUser(user.getUser_id(), user.getPseudo(), user.getPassword());
	}

	@RequestMapping("/stats/show/{name}") // get stats
	public List<Supervision> getStats(@PathVariable String name) {
		return serviceSupervision.getStats(name);
	}

	@RequestMapping("/partners/stats/{id}") // get detailled stats of a partner
	public List<Partner> getStats(@PathVariable int id) {
		return servicePartner.getStats(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/headers/add") // add a header
	public void addHeader(@RequestBody Headers headers) {
		serviceHeader.addHeader(headers);
	}

	@RequestMapping("/link/add/{userid}-{partnerid}") // add a right for an user to see a partner
	public void addLink(@PathVariable int userid, @PathVariable int partnerid) {
		Link link = new Link(userid, partnerid);
		serviceLink.addLink(link);
	}

	@RequestMapping("/link/delete/{userid}-{partnerid}") // delete a right for an user to see a partner
	public void deleteLink(@PathVariable int userid, @PathVariable int partnerid) {
		serviceLink.deleteLink(userid, partnerid);
	}

	@RequestMapping(path = "/mail/send") // send mail
	public void sendMail() throws MessagingException {
		serviceSupervision.sendMail("kais.fayech@sofrecom.com", "testmail", "hello!");
	}

	@RequestMapping("/curve/times/{partner_name}") // get response times of the supervision of a partner
	public List<Integer> getCurveTimes(@PathVariable String partner_name) {
		return serviceSupervision.getCurveTimes(partner_name);
	}

	@RequestMapping("/curve/dates/{partner_name}") // get dates of the supervision of a partner
	public List<String> getCurveDates(@PathVariable String partner_name) {
		return serviceSupervision.getCurveDates(partner_name);
	}

	@RequestMapping("/msg/show/{userid}") // show alerts of partners today
	public List<Msg> getmsgs(@PathVariable int userid) {
		return ServiceMsg.getMsgs(userid);
	}

	@RequestMapping("/msg/nbre/{partner_name}") // get nombre of a partner alerts today
	public int nbreAlarms(@PathVariable String partner_name) {
		return ServiceMsg.nbreAlarms(partner_name);
	}
	@RequestMapping("thread/modify/{thread}") // get nombre of a partner alerts today
	public void modifyThread(@PathVariable int thread) {
		 serviceSupervision.write(String.valueOf(thread*1000));	 
	}
	@RequestMapping("thread/get") // get nombre of a partner alerts today
	public int getThread() throws IOException {
		 return serviceSupervision.getThread();
    }
}