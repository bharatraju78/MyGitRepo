package com.vam.cco.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;

/**
 * JPA AttributeConverter to encrypt/decrypt BigDecimal values for storage in DB.
 * It converts the BigDecimal to String, encrypts using EncryptionUtil and stores as String in DB.
 */
@Converter(autoApply = false)
public class EncryptDecryptConverter implements AttributeConverter<BigDecimal, String> {

    @Override
    public String convertToDatabaseColumn(BigDecimal attribute) {
        if (attribute == null) return null;
        // Use plain string representation
        String plain = attribute.toPlainString();
        return EncryptionUtil.encrypt(plain);
    }

    @Override
    public BigDecimal convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        String decrypted = EncryptionUtil.decrypt(dbData);
        return new BigDecimal(decrypted);
    }
}
