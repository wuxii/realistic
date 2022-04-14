package com.realistic.loader

import java.sql.Connection

/**
 * @author wuxin
 */
interface MetadataLoader<M> {

    fun load(connection: Connection, properties: Map<String, Any>): List<M>

}
