package com.example.TradingApp.WebClient;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.time.Instant;
@Slf4j
@Component
public class DMarketWebApi {


    private final static String PUBLIC_KEY = "eae0b72d81fcbd370f947790e0ebca1cda1adcddceddb4f2fccadebee63545f2";
    private final static String PRIVATE_KEY = "d21e08041837d12c0e6e1f3e5d73cabf84db947380f31c573b294405c587320aeae0b72d81fcbd370f947790e0ebca1cda1adcddceddb4f2fccadebee63545f2";
    public final static String URL = "https://api.dmarket.com";
    private static final String timeStamp = getTimeStamp();
    private static final RestTemplate restTemplate = new RestTemplate();

    public static final HttpHeaders headers = new HttpHeaders();
    static {
        headers.set("X-Api-Key", PUBLIC_KEY);
        headers.set("X-Sign-Date", timeStamp);
    }
    public static String getResponse(String path, String method) {

        String string_to_sign =method +path + DMarketWebApi.getTimeStamp();
        String signature = DMarketWebApi.getSignature(string_to_sign);

        headers.set("X-Request-Sign", signature);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(DMarketWebApi.URL +path, HttpMethod.GET,httpEntity,String.class);
        return response.toString();
    }
    public static String getTimeStamp(){
        return Long.toString(Instant.now().getEpochSecond());
    }
    public static String getSignature(String string_to_sign) {

        String signature_prefix = "dmar ed25519 ";
        byte[] encoded = string_to_sign.getBytes(StandardCharsets.UTF_8);
        byte[] PRIVATE_KEY_bytes = hexStringToByteArray();
        byte[] signature_bytes = cryptoSign(encoded, PRIVATE_KEY_bytes);
        String signature = byteArrayToHexString(signature_bytes).substring(0, 128);

        return signature_prefix + signature;
    }

    private static byte[] hexStringToByteArray() {
        int len = DMarketWebApi.PRIVATE_KEY.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(DMarketWebApi.PRIVATE_KEY.charAt(i), 16) << 4)
                    + Character.digit(DMarketWebApi.PRIVATE_KEY.charAt(i + 1), 16));
        }
        return data;
    }
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
    private static byte[] cryptoSign(byte[] data, byte[] secretKey) {
        Security.addProvider(new BouncyCastleProvider());
        Ed25519PrivateKeyParameters privateKeyParams = new Ed25519PrivateKeyParameters(secretKey, 0);
        Ed25519Signer signer = new Ed25519Signer();
        signer.init(true, privateKeyParams);
        signer.update(data, 0, data.length);

        return signer.generateSignature();
    }
}
