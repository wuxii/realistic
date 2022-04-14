package com.realistic

import java.sql.JDBCType

interface JdbcTypeConverter {
    fun convert(jdbcType: JDBCType): Class<*>?

}

