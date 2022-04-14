package com.realistic.loader

import com.realistic.Row
import com.realistic.metadata.ColumnMetadata
import com.realistic.metadata.TableMetadata
import com.realistic.util.Utils
import java.sql.Connection
import java.util.stream.Collectors
import kotlin.streams.toList


/**
 * @author wuxin
 */
class TableMetadataLoader(var columnMetadataLoader: MetadataLoader<ColumnMetadata>) : MetadataLoader<TableMetadata> {

    constructor() : this(ColumnMetadataLoader())

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
            properties = mapOf("tableName" to (name as String))
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
