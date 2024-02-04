package br.com.martins.criptografia.component;

import jakarta.persistence.AttributeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Component
public class EncryptComponent implements AttributeConverter<String,String> {

    private static final String AES = "AES";
    private static final String SECRET = "secret-key-12345";
    private final Key key;
    private static Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        EncryptComponent.environment=environment;
    }

    public EncryptComponent() {
        // IMPORTANT NOTE: Preferably get the secret from the configuration
        // environment.getProperty() etc that loads from Vault or some Secret storage
        key = new SecretKeySpec(SECRET.getBytes(), AES);
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes()));
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException |
                 NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
            // You can decide to return an empty or null value on error to be stored if you don't want to throw exception
        }
    }
    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException  | NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
            // You can decide to return an empty or null value on error to be returned if you don't want to throw exception
        }
    }
}