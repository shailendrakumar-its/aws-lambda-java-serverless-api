package com.serverless.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Request {
	@SerializedName("content")
	@Expose
	private String content;
	@SerializedName("privateKey")
	@Expose
	private String privateKey;
	@SerializedName("publicKey")
	@Expose
	private String publicKey;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

}
