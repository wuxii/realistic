package com.realistic.controller

import com.realistic.core.loader.TableMetadataLoader
import com.realistic.core.metadata.TableMetadata
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource

/**
 * @author wuxin
 */
@RequestMapping("/table")
@RestController
class TableController(private val tableMetadataLoader: TableMetadataLoader, private val datasource: DataSource) {

    @GetMapping("/info/{tableName}")
    fun info(@PathVariable tableName: String): TableMetadata {
        return tableMetadataLoader.load(datasource.connection, mapOf("TABLE_NAME" to tableName))[0]
    }

}
