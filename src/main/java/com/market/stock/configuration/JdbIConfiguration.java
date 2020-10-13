package com.market.stock.configuration;

import com.market.stock.dao.ProductDao;
import com.market.stock.dao.ProductReservationDao;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class JdbIConfiguration {

    @Bean
    public Jdbi jdbi(DataSource ds, List<JdbiPlugin> jdbiPlugins, List<RowMapper<?>> rowMappers) {
        final var proxy = new TransactionAwareDataSourceProxy(ds);
        final var jdbi = Jdbi.create(proxy);

        jdbi.installPlugin(new SqlObjectPlugin());

        jdbiPlugins.forEach(jdbi::installPlugin);
        rowMappers.forEach(jdbi::registerRowMapper);

        return jdbi;
    }

    @Bean
    public ProductDao productDao(Jdbi jdbi) {
        return jdbi.onDemand(ProductDao.class);
    }

    @Bean
    public ProductReservationDao productReservationDao(Jdbi jdbi) {
        return jdbi.onDemand(ProductReservationDao.class);
    }
}
