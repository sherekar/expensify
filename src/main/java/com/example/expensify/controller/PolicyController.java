package com.example.expensify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensify.model.InputSettings;
import com.example.expensify.model.Policy;
import com.example.expensify.service.PolicyService;

@RestController
@RequestMapping("policy")
public class PolicyController {

	@Autowired
	PolicyService policyService;

	@PostMapping("/save")
	public Policy savePolicy(@RequestBody InputSettings inputSettings) {
		return policyService.savePolicy(inputSettings);
	}

	@GetMapping("/list")
	public List<Policy> getPolicyList() {
		return policyService.getPolicyList();
	}


}
