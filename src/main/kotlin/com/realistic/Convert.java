package com.realistic;

import com.realistic.core.JdbcTypeConverter;
import org.jetbrains.annotations.NotNull;

import java.sql.JDBCType;

public class Convert {


    public static JdbcTypeConverter mysql() {
        return new JdbcTypeConverter() {
            @NotNull
            @Override
            public Class<?> convert(@NotNull JDBCType jdbcType) {
                return Object.class;
            }
        };
    }

}
