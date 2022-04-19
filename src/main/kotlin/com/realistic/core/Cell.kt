package com.realistic.core

import com.realistic.core.metadata.ColumnMetadata

/**
 * @author wuxin
 */
class Cell(val value: Any?, val column: ColumnMetadata) {

    override fun toString(): String {
        return "Cell(name=${column.name}, value=${value})"
    }

}
