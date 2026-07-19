package com.restDemo.tenant;

import org.springframework.jdbc.datasource.DelegatingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TenantAwareDataSource extends DelegatingDataSource {

    public TenantAwareDataSource(DataSource targetDataSource) {
        super(targetDataSource);
    }

    @Override
    public Connection getConnection() throws SQLException {

        Connection con = super.getConnection();

        String tenant = TenantContext.getTenant();

        if (tenant == null || tenant.isBlank()) {
            tenant = "authentication";
        }

        System.out.println("Before Catalog : " + con.getCatalog());

        con.setCatalog(tenant);

        System.out.println("After Catalog : " + con.getCatalog());

        return con;
    }
}