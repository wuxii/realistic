package com.realistic.core.loader

import com.realistic.core.JdbcTypeConverter
import com.realistic.core.Row
import com.realistic.core.metadata.ColumnMetadata
import com.realistic.core.util.Utils
import java.sql.Connection
import java.sql.JDBCType

/**
 * @author wuxin
 */
class ColumnMetadataLoader(private val jdbcTypeConverter: JdbcTypeConverter) : MetadataLoader<ColumnMetadata> {

    override fun load(connection: Connection, properties: Map<String, Any>): List<ColumnMetadata> {
        val insensitiveProperties = properties.toSortedMap(String.CASE_INSENSITIVE_ORDER)
        val columnsRs = connection.metaData.getColumns(
            connection.catalog,
            connection.schema,
            insensitiveProperties["TABLE_NAME"]?.toString(),
            null
        )
        return Utils.allRows(columnsRs)
            .map { fetchColumnMetadata(it) }
            .toList()
    }

    private fun fetchColumnMetadata(row: Row): ColumnMetadata {
        val name = row.getValue("COLUMN_NAME")
        val nullable = row.getValue("IS_NULLABLE")
        val dataType = row.getValue("DATA_TYPE")
        val length = row.getValue("COLUMN_SIZE")
        val remarks = row.getValue("REMARKS")
        val defaultValue = row.getValue("COLUMN_DEF")
        val metadata = ColumnMetadata(name as String)
        metadata.remarks = if (remarks == null) "" else remarks as String
        metadata.length = if (length == null) -1 else length as Int
        metadata.sqlType = JDBCType.valueOf(dataType as Int)
        metadata.nullable = if (nullable == null) false else "YES" == nullable
        metadata.defaultValue = defaultValue
        metadata.javaType = jdbcTypeConverter.convert(metadata.sqlType)!!
        return metadata
    }

}
