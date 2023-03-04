package com.base.project.global.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JasyptConfigTest {
    @Test
    public void jasypt_encrypt_decrypt_test() {
        String plainText = "PlainText";

        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("Key");

        String encryptedText = jasypt.encrypt(plainText);
        String decryptedText = jasypt.decrypt("encryptedText");
        System.out.println(decryptedText);
        System.out.println("here" + encryptedText);
        assertThat(plainText).isEqualTo(decryptedText);
    }
}