package com.serverless;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;

import com.serverless.model.Request;
import com.serverless.model.Response;


public class Handler{
	private static final Logger LOG = Logger.getLogger(Handler.class);

	public static Object handleRequest(Request request, Context context) {
		LOG.info("received: " + request);
		Response resData = new Response();
		try {
			if(request !=null) {
				String sign = RSASignature.signByPrivateKey(request.getContent(), request.getPrivateKey());
				Boolean verify = RSASignature.verifySignByPublicKey(request.getContent(), sign, request.getPublicKey());
				resData.setStatus(true);
				resData.setSignature(sign);
				resData.setVerify(verify);
			}else {
				resData.setStatus(false);
				resData.setError("Invalid Data");
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			resData.setStatus(false);
			resData.setError(sw.toString());
		}
		
		Map<String, String> headers = new HashMap<>();
		headers.put("X-Powered-By", "AWS Lambda & Serverless");
		headers.put("Content-Type", "application/json");
		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(resData)
				.setHeaders(headers)
				.build();
	}
}
