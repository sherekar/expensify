package com.example.expensify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.expensify.model.CreatePolicyResponse;
import com.example.expensify.model.Credentials;
import com.example.expensify.model.InputSettings;
import com.example.expensify.model.Policy;
import com.example.expensify.model.RequestJobDescription;
import com.example.expensify.repository.PolicyRepository;

@Service
public class PolicyService {

	@Value("${expensify.partner.api.url}")
	private String expensifyURL;

	@Value("${expensify.partner.user.id}")
	private String partnerUserId;

	@Value("${expensify.partner.user.secret}")
	private String partnerUserSecret;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	PolicyRepository policyRepository; 

	public Policy savePolicy(InputSettings inputSettings) {
		RequestJobDescription requestJobDescription = new RequestJobDescription();
		requestJobDescription.setType("create");
		requestJobDescription.setCredentials(getCredentials());
		requestJobDescription.setInputSettings(inputSettings);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<RequestJobDescription> request = new HttpEntity<>(requestJobDescription, headers);

		ResponseEntity<CreatePolicyResponse> createPolicyResponse = restTemplate.postForEntity(expensifyURL, request, CreatePolicyResponse.class);
		System.out.println("createPolicyResponse===>"+createPolicyResponse);
		System.out.println("createPolicyResponse.getBody()===>"+createPolicyResponse.getBody());
		Policy policy = new Policy();
		if(createPolicyResponse != null) {
			if(createPolicyResponse.getBody().getPolicyID() != null) {
				policy.setPolicyId(createPolicyResponse.getBody().getPolicyID());
				if(createPolicyResponse.getBody().getPolicyName() != null) {
					policy.setPolicyName(createPolicyResponse.getBody().getPolicyName());
				}
			}
		}
		return policyRepository.save(policy);
	}

	public List<Policy> getPolicyList() {
		return policyRepository.findAll();
	}

	private Credentials getCredentials() {
		Credentials credentials = new Credentials();
		credentials.setPartnerUserID(partnerUserId);
		credentials.setPartnerUserSecret(partnerUserSecret);
		return credentials;
	}

}
