package ex.core.token.jwt;

import java.security.Key;

import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;

import ex.core.token.Token;
import ex.core.token.exception.TokenException;

@Singleton
public class TokenJWT implements Token {

	private final Logger logger = Logger.getLogger(TokenJWT.class);

	private JsonWebEncryption jwe;
	private Key key = new AesKey(ByteUtil.randomBytes(16));
	
	public TokenJWT() {
		logger.info("TokenJWT()");
	}

	public String getToken(String payload) throws TokenException {
		logger.info("getToken()");
		logger.debug("payload: " + payload);

		jwe = new JsonWebEncryption();
		jwe.setPayload(payload);
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		jwe.setKey(key);
		try {
			return jwe.getCompactSerialization();
		} catch (JoseException e) {
			throw new TokenException(e);
		}
	}

	public String getPayload(String token) throws TokenException {
		logger.info("getPayload()");
		logger.debug("token: " + token);

		jwe = new JsonWebEncryption();
		jwe.setKey(key);
		try {
			jwe.setCompactSerialization(token);
			return jwe.getPayload();
		} catch (JoseException e) {
			throw new TokenException(e);
		}
	}

	public static void main(String[] args) throws TokenException {
		TokenJWT t = new TokenJWT();
		String token = t.getToken("Hello!");
		System.out.println(token);
		String payload = t.getPayload(token);
		System.out.println(payload);
	}
}
