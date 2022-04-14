package com.realistic

import com.realistic.loader.TableMetadataLoader
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import javax.sql.DataSource

@ActiveProfiles("mysql")
@SpringBootTest
class RealisticApplicationTests {

    @Autowired
    lateinit var datasource: DataSource

    @Autowired
    lateinit var tableMetadataLoader: TableMetadataLoader

    @Test
    fun testLoader() {
        val tables = tableMetadataLoader.load(
            datasource.connection, mapOf(
                "TABLE_NAME_PATTERN" to "country"
            )
        )
        println("read ${tables.size} tables")
        for (table in tables) {
            println("$table has columns:")
            for (column in table.columns) {
                println(" $column")
            }
        }
    }

}
