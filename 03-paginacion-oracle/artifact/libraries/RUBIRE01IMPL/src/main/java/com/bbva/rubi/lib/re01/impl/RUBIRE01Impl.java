package com.bbva.rubi.lib.re01.impl;

import com.bbva.rubi.dto.ssai.CustomeDTO;
import com.bbva.rubi.dto.ssai.util.CustomerConstant;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * The RUBIRE01Impl class...
 */
public class RUBIRE01Impl extends RUBIRE01Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(RUBIRE01Impl.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		// TODO - Implementation of business logic
	}

	@Override
	public List<CustomeDTO> executeGet() {
		LOGGER.info("[PAPXRE01Impl] executeGet");
		Integer count = jdbcUtils.queryForInt(CustomerConstant.SQL_COUNT_ALL);

		double operacion = (Double.valueOf(count) / Double.valueOf(CustomerConstant.MAXIMO_REGISTRO));
		if (CustomerConstant.MAXIMO_REGISTRO > count) {
			operacion = 1;
		}

		int pages = (int) Math.ceil(operacion);

		List<CustomeDTO> listCustomerDTO = new ArrayList<>();
		List<Map<String, Object>> dataReturn;
		for (int pag = 1; pag <= pages; pag++) {
			int firstRow = (pag - 1) * CustomerConstant.MAXIMO_REGISTRO + 1;
			dataReturn = jdbcUtils.pagingQueryForList(CustomerConstant.SQL_SELECT_ALL, firstRow,
					CustomerConstant.MAXIMO_REGISTRO);
			listCustomerDTO.addAll(mappingResult(dataReturn));
		}
		return listCustomerDTO;
	}

	@Override
	public String executeGenerateCsv(List<CustomeDTO> listCustomers) {
		LOGGER.info("[PAPXRE01Impl] executeGenerateCsv");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("CUSTOMER_ID,FIRST_NAME,LAST_NAME,GENDER_TYPE,DOCUMENT_NUMBER_ID," +
				"DOCUMENT_TYPE,ADDRESS_DESC,NATIONALITY_NAME,EMAIL_DESC");
		stringBuilder.append(System.lineSeparator());
		for (CustomeDTO c : listCustomers) {
			stringBuilder.append(c.getCustomerId()).append(",").
					append(c.getFirstName()).append(",").
					append(c.getLastName()).append(",").
					append(c.getGender()).append(",").
					append(c.getDocumentNumber()).append(",").
					append(c.getDocumentType()).append(",").
					append(c.getAddress()).append(",").
					append(c.getNationality()).append(",").
					append(c.getEmail()).append(System.lineSeparator());
		}

		Base64 base64 = new Base64();
		String file = base64.encodeToString(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
		return file;
	}

	private List<CustomeDTO> mappingResult(List<Map<String, Object>> dataReturn) {
		List<CustomeDTO> returnTemp = new ArrayList<>();
		CustomeDTO customerDTO;
		for (Map<String, Object> data : dataReturn) {
			customerDTO = new CustomeDTO();
			customerDTO.setCustomerId((String) data.get("CUSTOMER_ID"));
			customerDTO.setFirstName((String) data.get("FIRST_NAME"));
			customerDTO.setLastName((String) data.get("LAST_NAME"));
			customerDTO.setGender((String) data.get("GENDER_TYPE"));
			customerDTO.setDocumentNumber((String) data.get("DOCUMENT_NUMBER_ID"));
			customerDTO.setDocumentType((String) data.get("DOCUMENT_TYPE"));
			customerDTO.setBirthDate((Date) data.get("BIRTH_DATE"));
			customerDTO.setAddress((String) data.get("ADDRESS_DESC"));
			customerDTO.setNationality((String) data.get("NATIONALITY_NAME"));
			customerDTO.setEmail((String) data.get("EMAIL_DESC"));
			returnTemp.add(customerDTO);
		}
		return returnTemp;
	}
}
