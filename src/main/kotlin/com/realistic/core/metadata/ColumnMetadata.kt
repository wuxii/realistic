package com.realistic.core.metadata

import java.sql.JDBCType
import java.util.Objects

/**
 * @author wuxin
 */
class ColumnMetadata(
    var name: String,
    var sqlType: JDBCType = JDBCType.VARCHAR,
    var nullable: Boolean = false,
    var length: Int? = -1,
    var javaType: Class<*> = Object::class.java,
    var remarks: String? = "",
    var defaultValue: Any? = null
) : Metadata {

    override fun toString(): String {
        return "Column($name, $sqlType)"
    }

}

