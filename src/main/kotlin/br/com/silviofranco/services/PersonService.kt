package br.com.silviofranco.services

import br.com.silviofranco.model.Person
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonService {

    private val counter: AtomicLong = AtomicLong()

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<Person> {
        logger.info("Finding all people!")

        val persons: MutableList<Person> = ArrayList()
        for (i in 0..7) {
            val person = mockPerson(i)
            persons.add(person)

        }
        return persons

    }

    fun findById(id: Long): Person {
        logger.info("Finding one person!")
        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Silvio"
        person.lastName = "Franco"
        person.address = "São Paulo - SP - Brasil"
        person.gender = "Male"
        return person
    }

    fun create(person: Person) = person

    fun update(person: Person) = person

    fun delete(id: Long){}

    fun mockPerson(i: Int): Person {
        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Person name $i"
        person.lastName = "Last name $i"
        person.address = "Some address in Brasil"
        person.gender = "Male"
        return person
    }
}