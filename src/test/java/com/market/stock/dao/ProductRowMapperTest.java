package com.market.stock.dao;

import com.market.stock.domain.Product;
import org.jdbi.v3.core.statement.StatementContext;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class ProductRowMapperTest {

    private static final int PRODUCT_ID = 1;
    private static final String PRODUCT_NAME = "PRODUCT_NAME";

    private ProductRowMapper productRowMapper;

    @Before
    public void setUp() {
        productRowMapper = new ProductRowMapper();
    }

    @Test
    public void testMap() throws Exception {
        final var mockedResultSet = mock(ResultSet.class);
        final var mockedStatementContext = mock(StatementContext.class);
        final var expectedProduct = new Product(PRODUCT_ID, PRODUCT_NAME);

        when(mockedResultSet.getInt(ProductRowMapper.PRODUCT_ID)).thenReturn(PRODUCT_ID);
        when(mockedResultSet.getString(ProductRowMapper.PRODUCT_NAME)).thenReturn(PRODUCT_NAME);

        final var product = productRowMapper.map(mockedResultSet, mockedStatementContext);

        assertEquals(expectedProduct, product);
        verify(mockedResultSet, times(1)).getInt(ProductRowMapper.PRODUCT_ID);
        verify(mockedResultSet, times(1)).getString(ProductRowMapper.PRODUCT_NAME);
        verifyZeroInteractions(mockedStatementContext);
    }
}