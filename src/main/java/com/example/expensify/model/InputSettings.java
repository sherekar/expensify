package com.example.expensify.model;

public class InputSettings {

	private String type;
	private String policyName;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	@Override
	public String toString() {
		return "InputSettings [type=" + type + ", policyName=" + policyName + "]";
	}

}
