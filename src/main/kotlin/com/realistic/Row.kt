package com.realistic

import java.util.*

/**
 * @author wuxin
 */
class Row(cells: List<Cell>) : Iterable<Cell> {

    private val cellMap: MutableMap<String, Cell> = mutableMapOf()

    init {
        cells.forEach {
            cellMap[it.column.name.uppercase(Locale.getDefault())] = it
        }
    }

    override fun iterator(): Iterator<Cell> {
        return cellMap.values.iterator()
    }

    fun get(field: String): Cell? {
        return cellMap[field.uppercase(Locale.getDefault())]
    }

    fun getValue(field: String): Any? {
        return get(field)?.value
    }

    fun toMap(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        for (entry in cellMap.entries) {
            map[entry.key] = entry.value.column
        }
        return map
    }

    override fun toString(): String {
        return "Rows[${cellMap.values}]"
    }

}
