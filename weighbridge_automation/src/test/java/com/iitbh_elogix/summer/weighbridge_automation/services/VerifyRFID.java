package com.iitbh_elogix.summer.weighbridge_automation.services;

import java.sql.Connection;
//import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.iitbh_elogix.summer.weighbridge_automation.dao.RFID_DAO;
//import com.iitbh_elogix.summer.weighbridge_automation.dao.RFID_DAO;
import com.iitbh_elogix.summer.weighbridge_automation.dto.RFID;

public class VerifyRFID implements RFID_DAO {

// public static final String URL = "jdbc:mysql://localhost:3306/wba_database";
// public static final String USER = "iitbh";
// public static final String PASS = "iitbh_2019";

	public static final String URL = "jdbc:mysql://remotemysql.com:3306/RAgvWhDgxD";
	public static final String USER = "RAgvWhDgxD";
	public static final String PASS = "VqqTV1rt2d";

	Connection connection = null;

	static RFID scannedRFID = null;

	public Connection getConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
// DriverManager.registerDriver(new Driver());
			connection = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException ex) {
			throw new RuntimeException("Error connecting to the database", ex);
		} catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public RFID getRFID(String uuid) {
		Connection connection = getConnection();

		try {

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM rfid WHERE rfid_uuid=" + "\'" + uuid + "\'");
			if (rs.next()) {
				RFID rfid = new RFID();
				rfid.setId(rs.getInt("rfid_pk"));
				rfid.setUUID(rs.getString("rfid_uuid"));
				System.out.println(rs.getString("rfid_uuid"));
				System.out.println(rs.getString("status"));
// (rs.getBoolean("status"));
				rfid.setStatus(rs.getBoolean("status"));
				return rfid;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public boolean isValid(RFID rfid) {
		return rfid.getStatus();
	}

	public boolean verify(String rfidUUID) {
		VerifyRFID obj = new VerifyRFID();
//		System.out.println("check");
//		scannedRFID = obj.getRFID("\'10E7CFD9\'");
		scannedRFID = obj.getRFID("\'" + rfidUUID + "\'");
//		System.out.println("check");
//		System.out.println((obj.isValid(scannedRFID)) ? "Valid" : "Not Valid");
		return obj.isValid(scannedRFID);
	}
}
