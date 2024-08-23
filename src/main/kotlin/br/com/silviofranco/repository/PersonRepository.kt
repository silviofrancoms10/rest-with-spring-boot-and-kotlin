package br.com.silviofranco.repository

import br.com.silviofranco.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository: JpaRepository<Person, Long?>{
}