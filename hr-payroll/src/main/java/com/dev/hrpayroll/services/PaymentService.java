package com.dev.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dev.hrpayroll.entities.Payment;
import com.dev.hrpayroll.entities.Worker;

@Service
public class PaymentService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${hr-worker.host}")
	private String workerHost;
	
	public Payment getPayment(long workerId, int days) {
		
		Map<String, String> uriVariables = new HashMap<>();
		
		uriVariables.put("id", String.format("%s", workerId));
		
		
		
		Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);
		
		return new Payment(worker.getName(), worker.getDaylyIncome(), days);
	}
}
