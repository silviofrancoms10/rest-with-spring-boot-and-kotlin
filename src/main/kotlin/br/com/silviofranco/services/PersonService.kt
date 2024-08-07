package br.com.silviofranco.services

import br.com.silviofranco.exceptions.ResourceNotFoundException
import br.com.silviofranco.model.Person
import br.com.silviofranco.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<Person> {
        logger.info("Finding all people!")

        return repository.findAll()
    }

    fun findById(id: Long): Person {
        logger.info("Finding one person!")

        return repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
    }

    fun create(person: Person): Person {
        logger.info("Creating a new person with name ${person.firstName}!")
        return repository.save(person)
    }

    fun update(person: Person) {
        logger.info("Updating person with ID ${person.id}!")
        val entity = repository.findById(person.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        person.firstName = person.firstName
        person.lastName = person.lastName
        person.address = person.address
        person.gender = person.gender

    }

    fun delete(id: Long) {
        logger.info("Deleting person with ID $id!")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }
}