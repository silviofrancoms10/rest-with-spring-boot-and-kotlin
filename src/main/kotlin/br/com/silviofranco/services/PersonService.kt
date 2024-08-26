package br.com.silviofranco.services

import br.com.silviofranco.data.vo.v1.PersonVO
import br.com.silviofranco.exceptions.ResourceNotFoundException
import br.com.silviofranco.model.Person
import br.com.silviofranco.repository.PersonRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository
    private val modelMapper = ModelMapper()
    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<PersonVO> {
        logger.info("Finding all people!")
        return modelMapper.map(repository.findAll(), Array<PersonVO>::class.java).toList()
    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding one person!")

        return modelMapper.map(
            repository.findById(id).orElseThrow { ResourceNotFoundException("No records found for this ID!") },
            PersonVO::class.java
        )
    }

    fun create(person: PersonVO): PersonVO {
        logger.info("Creating a new person with name ${person.firstName}!")
        val entity: Person = modelMapper.map(person, Person::class.java)
        return modelMapper.map(repository.save(entity), PersonVO::class.java)
    }

    fun update(person: PersonVO): PersonVO {
        logger.info("Updating person with ID ${person.id}!")
        val entity = repository.findById(person.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return modelMapper.map(repository.save(entity), PersonVO::class.java)
    }

    fun delete(id: Long) {
        logger.info("Deleting person with ID $id!")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }
}