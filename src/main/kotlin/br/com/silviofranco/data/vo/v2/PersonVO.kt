package br.com.silviofranco.data.vo.v2

import org.springframework.hateoas.RepresentationModel
import java.util.*

data class PersonVO (

    var key: Long =0,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = "",
    var birthDay: Date? = null
): RepresentationModel<PersonVO>()