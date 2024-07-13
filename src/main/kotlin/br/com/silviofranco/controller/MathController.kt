package br.com.silviofranco.controller

import br.com.silviofranco.converters.NumberConverter
import br.com.silviofranco.math.SimpleMath
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.UnsupportedOperationException
import java.util.concurrent.atomic.AtomicLong
import kotlin.text.isNullOrBlank
import kotlin.text.matches
import kotlin.text.replace
import kotlin.text.toDouble
import kotlin.text.toRegex

@RestController
class MathController {

    val counter: AtomicLong = AtomicLong()

    private val math: SimpleMath = SimpleMath()

    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) throw UnsupportedOperationException("Please set a numeric value!")
        return math.sum(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo))
    }

    @RequestMapping(value = ["/sub/{numberOne}/{numberTwo}"])
    fun sub(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) throw UnsupportedOperationException("Please set a numeric value!")
        return math.sub(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo))
    }

    @RequestMapping(value = ["/mul/{numberOne}/{numberTwo}"])
    fun mul(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) throw UnsupportedOperationException("Please set a numeric value!")
        return math.mul(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo))
    }

    @RequestMapping(value = ["/div/{numberOne}/{numberTwo}"])
    fun div(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) throw UnsupportedOperationException("Please set a numeric value!")
        return math.div(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo))
    }

    @RequestMapping(value = ["/avg/{numberOne}/{numberTwo}"])
    fun avg(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) throw UnsupportedOperationException("Please set a numeric value!")
        return math.avg(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo))
    }

    @RequestMapping(value = ["/sqrt/{number}"])
    fun sqrt(@PathVariable(value = "number") number: String?): Double {
        if (!NumberConverter.isNumeric(number)) throw UnsupportedOperationException("Please set a numeric value!")
        return math.sqrt(NumberConverter.converToDouble(number))
    }
}