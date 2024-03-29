package com.daas.datatx.core.datasource;

import com.daas.datatx.core.enums.DbType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * data source base class
 */
public abstract class BaseDataSource {

  private static final Logger logger = LoggerFactory.getLogger(BaseDataSource.class);

  /**
   * user name
   */
  protected String user;

  /**
   * user password
   */
  protected String password;

  /**
   * data source address
   */
  private String address;

  /**
   * database name
   */
  private String database;

  /**
   * other connection parameters for the data source
   */
  private String other;

  /**
   * principal
   */
  private String principal;

  public String getPrincipal() {
    return principal;
  }

  public void setPrincipal(String principal) {
    this.principal = principal;
  }

  /**
   * @return driver class
   */
  public abstract String driverClassSelector();

  /**
   * @return db type
   */
  public abstract DbType dbTypeSelector();

  /**
   * gets the JDBC url for the data source connection
   * @return getJdbcUrl
   */
  public String getJdbcUrl() {
    StringBuilder jdbcUrl = new StringBuilder(getAddress());

    appendDatabase(jdbcUrl);
    appendPrincipal(jdbcUrl);
    appendOther(jdbcUrl);

    return jdbcUrl.toString();
  }

  /**
   * append database
   * @param jdbcUrl jdbc url
   */
  protected void appendDatabase(StringBuilder jdbcUrl) {
    if (dbTypeSelector() == DbType.SQLSERVER) {
      jdbcUrl.append(";databaseName=").append(getDatabase());
    } else {
      if (getAddress().lastIndexOf('/') != (jdbcUrl.length() - 1)) {
        jdbcUrl.append("/");
      }
      jdbcUrl.append(getDatabase());
    }
  }

  /**
   * append principal
   * @param jdbcUrl jdbc url
   */
  private void appendPrincipal(StringBuilder jdbcUrl) {
    boolean tag = dbTypeSelector() == DbType.HIVE || dbTypeSelector() == DbType.SPARK;
    if (tag && StringUtils.isNotEmpty(getPrincipal())) {
      jdbcUrl.append(";principal=").append(getPrincipal());
    }
  }

  /**
   * append other
   * @param jdbcUrl jdbc url
   */
  private void appendOther(StringBuilder jdbcUrl) {
    String otherParams = filterOther(getOther());
    if (StringUtils.isNotEmpty(otherParams)) {
      String separator = "";
      switch (dbTypeSelector()) {
        case CLICKHOUSE:
        case MYSQL:
        case ORACLE:
        case POSTGRESQL:
          separator = "?";
          break;
        case DB2:
          separator = ":";
          break;
        case HIVE:
        case SQLSERVER:
          separator = ";";
          break;
        default:
          logger.error("Db type mismatch!");
      }
      jdbcUrl.append(separator).append(otherParams);
    }
  }

  protected String filterOther(String otherParams){
    return otherParams;
  }

  /**
   * test whether the data source can be connected successfully
   */
  public void isConnectable() {
    Connection con = null;
    try {
      Class.forName(driverClassSelector());
      con = DriverManager.getConnection(getJdbcUrl(), getUser(), getPassword());
    } catch (ClassNotFoundException | SQLException e) {
      logger.error("Get connection error: {}", e.getMessage());
    } finally {
      if (con != null) {
        try {
          con.close();
        } catch (SQLException e) {
          logger.error(e.getMessage(), e);
        }
      }
    }
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  public String getDatabase() {
    return database;
  }

  public void setDatabase(String database) {
    this.database = database;
  }

  public String getOther() {
    return other;
  }

  public void setOther(String other) {
    this.other = other;
  }


}
