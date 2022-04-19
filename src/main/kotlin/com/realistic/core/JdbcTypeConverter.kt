package com.realistic.core

import com.mysql.cj.MysqlType
import java.sql.JDBCType

interface JdbcTypeConverter {
    fun convert(jdbcType: JDBCType): Class<*>

    companion object {
        fun mysql(): JdbcTypeConverter = object : JdbcTypeConverter {
            override fun convert(jdbcType: JDBCType): Class<*> {
                val mysqlType = MysqlType.getByJdbcType(jdbcType.vendorTypeNumber)
                return if (mysqlType.className == null) Object::class.java else Class.forName(mysqlType.className).javaClass
            }
        }

    }

}

