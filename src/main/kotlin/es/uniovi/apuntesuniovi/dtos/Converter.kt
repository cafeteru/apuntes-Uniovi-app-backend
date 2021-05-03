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
                mapper = ModelMapper()
            }
            return mapper as ModelMapper
        }

        fun <T, D> convert(element: T, target: Class<D>): D {
            try {
                return getMapper().map(element, target)
            } catch (e: MappingException) {
                throw IllegalArgumentException(findRootCause(e).message)
            }
        }

        fun <T, D> convert(list: List<T>, target: Class<D>): List<D> {
            try {
                return list.map { element: T -> getMapper().map(element, target) }
            } catch (e: MappingException) {
                throw IllegalArgumentException(findRootCause(e).message)
            }
        }

        fun <T, D> convert(page: Page<T>, target: Class<D>): Page<D> {
            try {
                return page.map { element: T -> getMapper().map(element, target) }
            } catch (e: MappingException) {
                throw IllegalArgumentException(findRootCause(e).message)
            }
        }

        private fun findRootCause(throwable: Throwable): Throwable {
            Objects.requireNonNull(throwable)
            var rootCause = throwable
            while (rootCause.cause != null && rootCause.cause !== rootCause) {
                rootCause = rootCause.cause!!
            }
            return rootCause
        }
    }
}