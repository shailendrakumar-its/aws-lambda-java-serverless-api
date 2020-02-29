package com.serverless;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.encoders.UrlBase64;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.TreeMap;
public class RSASignature {

	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";
	public static final String ENCODING = "utf-8";
	/**
	 * get private key
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(key.getBytes(ENCODING));
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}
	/**
	 * get puhlic key
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String key) throws Exception {byte[] keyBytes = Base64.decodeBase64(key);
	X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
	KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	PublicKey publicKey = keyFactory.generatePublic(keySpec);
	return publicKey;
	}
	/**
	 * sign by private key
	 *
	 * @param content
signContent
	 * @param privateKey
	 * @return sign
	 */
	public static String signByPrivateKey(String content, String privateKey) {
		try {
			PrivateKey priKey = getPrivateKey(privateKey);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(priKey);
			signature.update(content.getBytes(ENCODING));
			byte[] signed = signature.sign();
			return new String(UrlBase64.encode(signed), ENCODING);
		} catch (Exception e) {
			throw new RuntimeException("sign error", e);
		}
	}
	public static boolean verifySignByPublicKey(String content, String sign,
			String publicKey) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decodeBase64(publicKey.getBytes(ENCODING));
			PublicKey pubKey = keyFactory.generatePublic(new
					X509EncodedKeySpec(encodedKey));
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(pubKey);
			signature.update(content.getBytes(ENCODING));
			return signature.verify(UrlBase64.decode(sign.getBytes(ENCODING)));
		} catch (Exception e) {
			throw new RuntimeException("verfify sign error", e);}
	}
	public static String encryptByPublicKey(String plainText, String publicKey) {
		try {
			PublicKey pubKey = getPublicKey(publicKey);
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			byte[] enBytes = cipher.doFinal(plainText.getBytes(ENCODING));
			return new String(UrlBase64.encode(enBytes), ENCODING);
		} catch (Exception e) {
			throw new RuntimeException("encrypt error", e);
		}
	}
	public static String decryptByPrivateKey(String enStr, String privateKey) {
		try {
			PrivateKey priKey = getPrivateKey(privateKey);
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, priKey);
			byte[] deBytes = cipher.doFinal(UrlBase64.decode(enStr.getBytes(ENCODING)));
			return new String(deBytes);
		} catch (Exception e) {
			throw new RuntimeException("decrypt error", e);
		}
	}
	public static void main (String[] args) {
		String sign =
				signByPrivateKey("createTime=1514765288&notifyUrl=http://oneboxhost/mock/notify&orderDesc=QmonXLlbbD&outOrderId=20180101080808000001&partnerId=10000001"
						,
						"MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMMoZzPcaK7ie4yrapatAIVhD"
								+ "OFtUPy8rhhHMfn61YjcMm28Dz3a9oqnSHq6wjP0XxcUFRFuwGaDFPDv/L49cNQ+6bQ2PaNQvL"
								+ "2LT86A6/R+aQM+u1ONbP8iGYJ63vpn6kk5+WY5TpyXsFzaDTjUyURrYjJRcryeEN7odGdVhOnZAg"
								+ "MBAAECgYAYrWA3Z5R5ILxcskQ9H00kkHwPeUI3Yyhke4QvRu9/adCanaATwz9Pkw2QL1NlPG5Vv"
								+ "b1YQffkPokEWmRMLfq5MznA00/CE+JXhp/SEi3w79s1K3ExUzI1OpGSvN08XeS68wpuiAJHnzslOl"
								+ "lQ8r2+/2oyo1UmzPEDlSY0rAqaTQJBAPBwWPfoeFdrrLRLp2Ey4nLLdYJw/s8mdQyiVjDqW/tkKzP"
								+ "23tCgliTu5/9ClP3Qr41iRdHD6WJ4ctJ1d3JWUK8CQQDPydPvgoFD//4+fi9HgtoTZUyIqLZpNs1nZY"
								+ "Y7b+59yhWBIfWGP8DFhZrd/MyNpLfsoug4JOOqEe8nlG2EPj/3AkEApxMqf3oGxZiItfAsKxqUyHg"
								+ "g+7dRGNj8VP8pLWxs5k9AxicxxX8RVjC8/V9i8MxmcLRtF8ovDsHr59rAWa8o+QJBAJjPoDpjKrec"
								+ "mxjQaerYc5KSC+/wy32jHPoucsJhde4yYRA/rjYVyqo4sIUS9kgw3EZ+I/OuRXP8jnn4MXZw5U8CQQC0V3ugUJLBc/uLpXzDgLc1ISNRfPI61odmt3hqs421thIValXhVBXhHt/OkHhUVRIb4dDC85QPHX"
								+ "3Q+RWma6cp");
		System.out.println(String.format("sign:%s", sign));
		System.out.println(verifySignByPublicKey("createTime=1514765288&notifyUrl=ht"
				+ "tp://oneboxhost/mock/notify&orderDesc=QmonXLlbbD&outOrderId=2018010108080800000"
				+ "1&partnerId=10000001", sign,
				"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDKGcz3Giu4nuMq2qWrQCFYQzhbVD8v"
						+ "K4YRzH5+tWI3DJtvA892vaKp0h6usIz9F8XFBURbsBmgxTw7/y+PXDUPum0Nj2jULy9i0/OgOv0f"
						+ "mkDPrtTjWz/IhmCet76Z+pJOflmOU6cl7Bc2g041MlEa2IyUXK8nhDe6HRnVYTp2QIDAQAB"));
	}
}
