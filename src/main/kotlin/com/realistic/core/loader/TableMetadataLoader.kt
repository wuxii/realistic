package com.realistic.core.loader

import com.realistic.core.JdbcTypeConverter
import com.realistic.core.Row
import com.realistic.core.metadata.ColumnMetadata
import com.realistic.core.metadata.TableMetadata
import com.realistic.core.util.Utils
import java.sql.Connection
import java.util.stream.Collectors


/**
 * @author wuxin
 */
class TableMetadataLoader(var columnMetadataLoader: MetadataLoader<ColumnMetadata>) : MetadataLoader<TableMetadata> {

    constructor() : this(ColumnMetadataLoader(JdbcTypeConverter.mysql()))

    override fun load(connection: Connection, properties: Map<String, Any>): List<TableMetadata> {
        val databaseMetadata = connection.metaData
        val insensitiveProperties = properties.toSortedMap(String.CASE_INSENSITIVE_ORDER)
        val tablesRs = databaseMetadata.getTables(
            connection.catalog,
            connection.schema,
            insensitiveProperties["TABLE_NAME_PATTERN"]?.toString(),
            arrayOf("TABLE")
        )
        return Utils
            .allRows(tablesRs)
            .stream()
            .map { fetchTableMetadata(connection, it) }
            .collect(Collectors.toList())
    }

    private fun fetchTableMetadata(conn: Connection, row: Row): TableMetadata {
        val name = row.getValue("TABLE_NAME")
        val category = row.getValue("TABLE_CAT")
        val schema = row.getValue("TABLE_SCHEM")
        val remarks = row.getValue("REMARKS")
        val columns = columnMetadataLoader.load(
            connection = conn,
            properties = mapOf("TABLE_NAME" to (name as String))
        )
        return TableMetadata(
            name = name,
            category = category?.toString(),
            schema = schema?.toString(),
            remarks = if (remarks == null) "" else remarks as String,
            columns = columns
        )
    }

}
