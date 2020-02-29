package com.serverless.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Response {
	@SerializedName("status")
	@Expose
	private Boolean status;
	@SerializedName("signature")
	@Expose
	private String signature;
	@SerializedName("verify")
	@Expose
	private Boolean verify;
	@SerializedName("error")
	@Expose
	private String error;

	public Response() {

	}
	public Response(String signature, Boolean verify) {
		this.signature = signature;
		this.verify = verify;
	}

	public Response(Boolean status, String error) {
		this.status = status;
		this.error = error;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Boolean getVerify() {
		return verify;
	}

	public void setVerify(Boolean verify) {
		this.verify = verify;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
