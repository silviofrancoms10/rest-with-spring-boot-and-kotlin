package br.com.silviofranco.services

import br.com.silviofranco.controller.PersonController
import br.com.silviofranco.data.vo.v1.PersonVO
import br.com.silviofranco.exceptions.ResourceNotFoundException
import br.com.silviofranco.mapper.custom.Person2Mapper
import br.com.silviofranco.mapper.custom.PersonMapper
import br.com.silviofranco.model.Person
import br.com.silviofranco.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger
import br.com.silviofranco.data.vo.v2.PersonVO as PersonVOV2

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    @Autowired
    private lateinit var person2Mapper: Person2Mapper

    @Autowired
    private lateinit var personMapper: PersonMapper

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<PersonVO> {
        logger.info("Finding all people!")
        val persons = repository.findAll()
        val vos = persons.map { personMapper.mapEntityToVO(it) }
        for (person in vos) {
            val withSelfRel = linkTo(PersonController::class.java).slash(person.key).withSelfRel()
            person.add(withSelfRel)
        }
        return vos
    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding one person with ID! $id")
        val person = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        val personVO: PersonVO = personMapper.mapEntityToVO(person)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun create(person: PersonVO): PersonVO {
        logger.info("Creating a new person with name ${person.firstName}!")
        val entity: Person = personMapper.mapVOToEntity(person)
        val personVO: PersonVO = personMapper.mapEntityToVO(repository.save(entity))
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun createV2(person: PersonVOV2): PersonVOV2 {
        logger.info("Creating a new person with name ${person.firstName}!")
        val entity: Person = person2Mapper.mapVOToEntity(person)
        val personVOV2: PersonVOV2 = person2Mapper.mapEntityToVO(repository.save(entity))
        val withSelfRel = linkTo(PersonController::class.java).slash(personVOV2.key).withSelfRel()
        personVOV2.add(withSelfRel)
        return personVOV2
    }

    fun update(person: PersonVO): PersonVO {
        logger.info("Updating person with ID ${person.key}!")
        val entity = repository.findById(person.key)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        val personVO: PersonVO = personMapper.mapEntityToVO(repository.save(entity))
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun delete(id: Long) {
        logger.info("Deleting person with ID $id!")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }
}