package com.realistic

import com.mysql.cj.MysqlType
import java.sql.JDBCType
import kotlin.reflect.KClass

interface JdbcTypeConverter {

    fun convert(jdbcType: JDBCType): KClass<*>

}

