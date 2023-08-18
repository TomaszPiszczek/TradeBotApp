package com.example.TradingApp.WebClient;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.time.Instant;

@Service
@Slf4j
public class DMarketWebApi {


    private final String PUBLIC_KEY = "eae0b72d81fcbd370f947790e0ebca1cda1adcddceddb4f2fccadebee63545f2";
    private final String PRIVATE_KEY = "d21e08041837d12c0e6e1f3e5d73cabf84db947380f31c573b294405c587320aeae0b72d81fcbd370f947790e0ebca1cda1adcddceddb4f2fccadebee63545f2";
    private final String URL = "https://api.dmarket.com/account/v1/user";

    RestTemplate restTemplate = new RestTemplate();

    public void getCall() throws Exception {
        String nonce = Long.toString(Instant.now().getEpochSecond());
        String api_url_path = "/account/v1/user";
        String method = "GET";
        String string_to_sign = method + api_url_path + nonce;
        String signature_prefix = "dmar ed25519 ";

        byte[] encoded = string_to_sign.getBytes(StandardCharsets.UTF_8);

        byte[] PRIVATE_KEY_bytes = hexStringToByteArray(PRIVATE_KEY);
        byte[] signature_bytes = cryptoSign(encoded, PRIVATE_KEY_bytes);
        String signature = byteArrayToHexString(signature_bytes).substring(0, 128);

        HttpHeaders headers = new HttpHeaders();

        headers.set("X-Api-Key", PUBLIC_KEY);
        headers.set("X-Request-Sign", signature_prefix + signature);
        headers.set("X-Sign-Date", nonce);

        log.info("signature " + signature);
        log.info("timestamp " + nonce);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET,httpEntity,String.class);
        log.info(response.toString());
    }
    public static byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
    public static byte[] cryptoSign(byte[] data, byte[] secretKey) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        Ed25519PrivateKeyParameters privateKeyParams = new Ed25519PrivateKeyParameters(secretKey, 0);

        Ed25519Signer signer = new Ed25519Signer();
        signer.init(true, privateKeyParams);
        signer.update(data, 0, data.length);
        return signer.generateSignature();
    }
}
