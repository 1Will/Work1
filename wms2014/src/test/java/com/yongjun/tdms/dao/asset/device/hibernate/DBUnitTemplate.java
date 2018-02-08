package com.yongjun.tdms.dao.asset.device.hibernate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

/**
 * @author qs
 * @version $Id: DBUnitTemplate.java 7614 2007-09-26 01:49:17Z qsun $
 */
public class DBUnitTemplate {
	private BasicDataSource dataSource;
	private IDatabaseConnection connection;

	public DBUnitTemplate() {

	}

	public void setDataSource(BasicDataSource dataSource) throws SQLException {

		this.dataSource = dataSource;

		connection = new DatabaseConnection(this.dataSource.getConnection(),
				"eam2008_test");

	}

	public void execute(String path, DatabaseOperation oper)
			throws FileNotFoundException, IOException, DatabaseUnitException,
			SQLException {

		Reader reader = Resources.getResourceAsReader(path);

		//IDataSet dataset = new FlatXmlDataSet(new FileInputStream(path));
		IDataSet dataSet = new FlatXmlDataSet(reader);

		oper.execute(connection, dataSet);

	}
}
