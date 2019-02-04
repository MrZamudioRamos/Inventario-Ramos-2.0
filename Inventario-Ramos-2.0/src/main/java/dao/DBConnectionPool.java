/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import config.ConfiguracionYaml;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DBConnectionPool {

    private static DBConnectionPool dbconection = null;
    private static String Driver = "com.mysql.cj.jdbc.Driver";
    private static String ulr = "jdbc:mysql://dam2.mysql.iesquevedo.es:3335/ramos_inventario?zeroDateTimeBehavior=convertToNull";
    private static String user = "root";
    private static String pass = "root";

    private DataSource hirakiDatasource = null;

    DBConnectionPool() {
        hirakiDatasource = getDataSourceHikari();
    }

    public static DBConnectionPool getInstance() {
        if (dbconection == null) {
            dbconection = new DBConnectionPool();
        }

        return dbconection;
    }

    public Connection getConnection() throws Exception {
        Class.forName(Driver);
        Connection connection;
        connection = hirakiDatasource.getConnection();

        return connection;
    }

    private DataSource getDataSourceHikari() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(ulr);
        config.setUsername(user);
        config.setPassword(pass);
        config.setDriverClassName(Driver);
        config.setMaximumPoolSize(10);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource datasource = new HikariDataSource(config);

        return datasource;
    }

    public DataSource getDataSource() {

        return hirakiDatasource;
    }

    public void cerrarConexion(Connection connection) {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarPool() {
        hirakiDatasource = null;
    }
}
