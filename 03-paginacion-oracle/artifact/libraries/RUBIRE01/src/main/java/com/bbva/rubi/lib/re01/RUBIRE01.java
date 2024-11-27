package com.bbva.rubi.lib.re01;
import com.bbva.rubi.dto.ssai.CustomeDTO;

import java.util.List;

/**
 * The  interface RUBIRE01 class...
 */
public interface RUBIRE01 {

	/**
	 * The execute method...
	 */
	void execute();

	List<CustomeDTO> executeGet();
	String executeGenerateCsv(List<CustomeDTO> listCustomers);

}
