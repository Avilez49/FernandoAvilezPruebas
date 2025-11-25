package com.calidad.prueba.unnittest.login.integracion;

import static org.junit.jupiter.api.Assertions.fail;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterEach; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.calidad.login.service.UserService;
import com.calidad.login.dao.IDAOLogin;
import com.calidad.login.dao.UserMysqlDAO;
import com.calidad.login.modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;

import com.calidad.login.service.UserService;
//@ExtendWith(DBUnitextension.class)

public class LoginServiceTest extends DBTestCase {
    
    private IDAOLogin dao;
    private UserService service;
    
    public LoginServiceTest() {
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.cj.jdbc.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3307/calidad");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,"root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"123456");

    }

    @BeforeEach
    void setup() throws Exception{

        dao = new UserMysqlDAO();
        service = new UserService(dao);

        IDatabaseConnection connection = getConnection();
        if (connection == null){
            fail("Frailed to establish a connection to the database.");
        } else {
            System.out.println("Connection established succesfully");
        }
        try{
            DatabaseOperation.TRUNCATE_TABLE.execute(connection, getDataSet());
            DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
        } catch (Exception e){
            fail("Error in setup"+ e.getMessage());
        } finally{
            connection.close();
        }
    }

protected IDataSet getDataSet() throws Exception {
    // Carga el estado inicial de la base de datos
    return new FlatXmlDataSetBuilder().build(new FileInputStream("src/resources/initDB.xml")); 
}
    @Test
    public void WhenSaverUser_test(){
        service.createUser("usuario1", "usuario@mail.com", "12345678");

        try{
            IDatabaseConnection conn = getConnection();
            conn.getConfig().setProperty(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true);
            IDataSet databaseDataSet = conn.createDataSet();
            ITable actualTable = databaseDataSet.getTable("Usuario");
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/addUser.xml"));
            ITable expectedTable = expectedDataSet.getTable("Usuario");
            Assertion.assertEquals(expectedTable, actualTable);

        }catch(Exception e){
            fail("Error in insert test:" + e.getMessage());
        }
    }
}