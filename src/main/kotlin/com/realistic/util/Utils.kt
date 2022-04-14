package com.realistic.util

import com.realistic.Cell
import com.realistic.Row
import com.realistic.metadata.ColumnMetadata
import java.sql.JDBCType
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.util.function.Predicate
import java.util.stream.Collectors
import kotlin.streams.toList

/**
 * @author wuxin
 */
class Utils {

    companion object {

        fun allRows(rs: ResultSet): List<Row> = rows(rs) { true }

        private fun rows(rs: ResultSet, predicate: Predicate<ColumnMetadata>): List<Row> {
            val columns = columns(rs.metaData)
            val rows = mutableListOf<Row>()
            while (rs.next()) {
                val cells = columns
                    .stream()
                    .filter(predicate)
                    .map { Cell(rs.getObject(it.name), it) }
                    .collect(Collectors.toList())
                rows.add(Row(cells))
            }
            return rows
        }

        private fun columns(rsMetadata: ResultSetMetaData): List<ColumnMetadata> {
            val columns = mutableListOf<ColumnMetadata>()
            for (i in 1..rsMetadata.columnCount) {
                val column = ColumnMetadata(rsMetadata.getColumnName(i))
                column.sqlType = JDBCType.valueOf(rsMetadata.getColumnType(i))
                column.javaType = rsMetadata.getColumnClassName(i)
                column.length = rsMetadata.getColumnDisplaySize(i)
                column.nullable = rsMetadata.isNullable(i) != 1
                columns.add(column)
            }
            return columns
        }

    }

}
