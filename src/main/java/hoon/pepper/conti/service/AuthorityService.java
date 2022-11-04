package hoon.pepper.conti.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class AuthorityService {
    @Value("${spring.admin.password}")
    private String adminPassword;

    public Boolean validAdminPassword(String password) {
        return password.equals(adminPassword) || password.equals(this.decode(adminPassword));
    }

    private String decode(String msg) {
        return new String(Base64.decodeBase64(msg.getBytes(StandardCharsets.UTF_8)));
    }
}
