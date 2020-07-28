package es.uniovi.apuntesuniovi.servicies.dtos

import es.uniovi.apuntesuniovi.log.LogService
import java.util.*
import java.util.function.Consumer

/**
 * Clase abstracta para definir los métodos de conversión de entidades y dto
 */
abstract class AbstractDtoAssembler<Entity, Dto> {
    protected val logService = LogService(this.javaClass)

    /**
     * Convierte una entidad en un dto
     *
     * @param entity Entidad a convertir
     */
    abstract fun entityToDto(entity: Entity): Dto

    /**
     * Convierte un dto en una entidad
     *
     * @param dto Dto a convertir
     */
    abstract fun dtoToEntity(dto: Dto): Entity

    /**
     * Convierte una lista de entidades en una lista de dtos
     *
     * @param entityList Lista de entidades a convertir
     */
    fun listToDto(entityList: List<Entity>): List<Dto> {
        logService.info("listToDto(entityList: ${entityList}) - start")
        val dtoList: MutableList<Dto> = ArrayList()
        entityList.forEach(Consumer { entity -> dtoList.add(entityToDto(entity)) })
        logService.info("listToDto(entityList: ${entityList}) - end")
        return dtoList
    }

    /**
     * Convierte una lista de dtos en una lista de entidades
     *
     * @param dtoList Lista de dtos a convertir
     */
    fun listToEntities(dtoList: List<Dto>): List<Entity> {
        logService.info("listToEntities(dtoList: ${dtoList}) - start")
        val entityList: MutableList<Entity> = ArrayList()
        dtoList.forEach(Consumer { dto -> entityList.add(dtoToEntity(dto)) })
        logService.info("listToEntities(dtoList: ${dtoList}) - end")
        return entityList
    }
}