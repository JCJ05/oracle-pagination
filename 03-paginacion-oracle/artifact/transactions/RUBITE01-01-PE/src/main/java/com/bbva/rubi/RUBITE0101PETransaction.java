package com.bbva.rubi;

import com.bbva.elara.domain.transaction.Severity;
import com.bbva.rubi.dto.ssai.CustomeDTO;
import com.bbva.rubi.lib.re01.RUBIRE01;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Generacion de archivo CSV
 *
 */
public class RUBITE0101PETransaction extends AbstractRUBITE0101PETransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(RUBITE0101PETransaction.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		LOGGER.info("[RUBITE0101PETransaction] execute");
		RUBIRE01 rubiRE01 = this.getServiceLibrary(RUBIRE01.class);

		List<CustomeDTO> customers = rubiRE01.executeGet();
		String hash = rubiRE01.executeGenerateCsv(customers);

		if(this.getAdviceList().isEmpty()){
			this.setHash(hash);
			this.setSeverity(Severity.OK);

		}else {

			this.setSeverity(Severity.ENR);
		}


	}

}
