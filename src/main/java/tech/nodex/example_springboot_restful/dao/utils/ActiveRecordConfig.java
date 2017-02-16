package tech.nodex.example_springboot_restful.dao.utils;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import tech.nodex.example_springboot_restful.dao._MappingKit;

import javax.sql.DataSource;

/**
 * Created by cz on 2017-2-6.
 */
public class ActiveRecordConfig {
    private String jdbcUrl,dbUser,dbPassword;
    private int initialSize = 1;
    private int minIdle = 1;
    private int maxActive = 20;
    private int maxWait = 30000;
    private DataSource dataSource;
    private ActiveRecordPlugin activeRecordPlugin;

    public ActiveRecordConfig(String jdbcUrl, String dbUser, String dbPassword) {
        this.jdbcUrl = jdbcUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public synchronized ActiveRecordPlugin  connect() {
        if(dataSource==null){
            DruidPlugin druidPlugin = new DruidPlugin(jdbcUrl, dbUser,dbPassword);
            druidPlugin.setInitialSize(initialSize);
            druidPlugin.setMinIdle(minIdle);
            druidPlugin.setMaxActive(maxActive);
            druidPlugin.setMaxWait(maxWait);
            druidPlugin.start();
            this.dataSource = druidPlugin.getDataSource();
            ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin("xvp", druidPlugin.getDataSource());
            _MappingKit.mapping(activeRecordPlugin);
            activeRecordPlugin.start();

            this.activeRecordPlugin = activeRecordPlugin;
        }
        return this.activeRecordPlugin;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public ActiveRecordPlugin getActiveRecordPlugin() {
        return activeRecordPlugin;
    }
}
