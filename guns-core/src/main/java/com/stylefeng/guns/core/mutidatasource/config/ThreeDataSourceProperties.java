package com.stylefeng.guns.core.mutidatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 默认多数据源配置
 *
 * @author fengshuonan
 * @date 2017-08-16 10:02
 */
@Component
@ConfigurationProperties(prefix = "guns.three-datasource")
public class ThreeDataSourceProperties {

    private String defaultDataSourceName = "dataSourceMes";

    private String url = "jdbc:mysql://127.0.0.1:3306/biz?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";

    private String username = "root";

    private String password = "root";

    private String driverClassName = "com.mysql.jdbc.Driver";

    private String validationQuery = "SELECT 'x' from dual";

    private int validationQueryTimeout=1;


    public void config(DruidDataSource dataSource) {
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setValidationQueryTimeout(validationQueryTimeout);
        dataSource.setValidationQuery(validationQuery);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDefaultDataSourceName() {
        return defaultDataSourceName;
    }

    public void setDefaultDataSourceName(String defaultDataSourceName) {
        this.defaultDataSourceName = defaultDataSourceName;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public int getValidationQueryTimeout() {
        return validationQueryTimeout;
    }

    public void setValidationQueryTimeout(int validationQueryTimeout) {
        this.validationQueryTimeout = validationQueryTimeout;
    }
}
