package com.realistic.core.metadata

/**
 * @author wuxin
 */
class TableMetadata(
    val name: String,
    val category: String? = null,
    val schema: String? = null,
    val remarks: String? = null,
    val columns: List<ColumnMetadata>
) : Metadata {

    override fun toString(): String {
        return "Table($name, has ${columns.size} columns)"
    }

}
