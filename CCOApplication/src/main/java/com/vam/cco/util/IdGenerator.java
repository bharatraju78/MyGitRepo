package com.vam.cco.util;

import java.math.BigInteger;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {
	
	private Logger logger = LoggerFactory.getLogger(IdGenerator.class);
	
	public String generateAccountId() {
		logger.info("IdGenerator::generateAccountId::start");
		UUID uuid = UUID.randomUUID();
        BigInteger bigInt = new BigInteger(1, uuid.toString().replace("-", "").getBytes());
        logger.info("IdGenerator::generateAccountId::bigInt = {}", bigInt);
        String id = String.format("%010d", bigInt.mod(BigInteger.TEN.pow(10)));
		logger.info("IdGenerator::generateAccountId::id = {}", id);
		logger.info("IdGenerator::generateAccountId::end");
		return id;	
	}

}
