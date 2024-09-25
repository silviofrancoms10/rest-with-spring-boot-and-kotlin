package br.com.silviofranco.data.vo.v1

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.hateoas.RepresentationModel

@JsonPropertyOrder("id", "address", "firstName", "lastName","gender") // serve para retornar o objeto nessa sequencia na requisição
data class PersonVO (

    var key: Long =0,
    @field:JsonProperty("first_name")  // serve para retornar o objeto com esse nome na requisição Postman
    var firstName: String = "",
    @field:JsonProperty("last_name")    // serve para retornar o objeto com esse nome na requisição Postman
    var lastName: String = "",
    var address: String = "",
    @field:JsonIgnore   // omitir no retorno
    var gender: String = ""

) : RepresentationModel<PersonVO>()

