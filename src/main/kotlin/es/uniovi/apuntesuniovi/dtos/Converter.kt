package es.uniovi.apuntesuniovi.dtos

import org.modelmapper.MappingException
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Page
import java.util.*

class Converter {
    companion object {
        private var mapper: ModelMapper? = null

        private fun getMapper(): ModelMapper {
            if (mapper == null) {
                val mapper = ModelMapper()
                // mapper.addConverter(LearnSubjectConverter().dtoToEntity())
                this.mapper = mapper
            }
            return mapper as ModelMapper
        }

        fun <D> convert(source: Any?, target: Class<D>?): D {
            try {
                return getMapper().map(source, target)
            } catch (e: MappingException) {
                throw IllegalArgumentException(findRootCause(e).message)
            }
        }

        fun <T, D> convert(source: List<T>, target: Class<D>?): List<D> {
            try {
                return source.map { s: T -> getMapper().map(s, target) }
            } catch (e: MappingException) {
                throw IllegalArgumentException(findRootCause(e).message)
            }
        }

        fun <T, D> convert(source: Page<T>, target: Class<D>?): Page<D> {
            try {
                return source.map { s: T -> getMapper().map(s, target) }
            } catch (e: MappingException) {
                throw IllegalArgumentException(findRootCause(e).message)
            }
        }

        private fun findRootCause(throwable: Throwable?): Throwable {
            Objects.requireNonNull(throwable)
            var rootCause = throwable
            while (rootCause!!.cause != null && rootCause.cause !== rootCause) {
                rootCause = rootCause.cause
            }
            return rootCause
        }
    }
}