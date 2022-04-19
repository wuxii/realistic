package com.realistic

import com.realistic.core.loader.TableMetadataLoader
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class RealisticApplication {

    @Bean
    fun tableMetadataLoader(): TableMetadataLoader {
        return TableMetadataLoader()
    }

}

fun main(args: Array<String>) {
    runApplication<RealisticApplication>(*args)
}
