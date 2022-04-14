package com.realistic.metadata

import java.sql.JDBCType

/**
 * @author wuxin
 */
class ColumnMetadata(
    var name: String,
    var sqlType: JDBCType = JDBCType.VARCHAR,
    var nullable: Boolean = false,
    var length: Int? = -1,
    var javaType: String? = "",
    var remarks: String? = "",
    var defaultValue: Any? = null
) : Metadata {

    override fun toString(): String {
        return "Column($name, $sqlType)"
    }

}

