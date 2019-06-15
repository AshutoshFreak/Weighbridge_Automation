package com.iitbh_elogix.summer.weighbridge_automation.dao;

import com.iitbh_elogix.summer.weighbridge_automation.dto.RFID;

public interface RFID_DAO {

	RFID getRFID(String uuid);

	boolean isValid(RFID rfid);

}
