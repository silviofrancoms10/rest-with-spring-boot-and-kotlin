package br.com.silviofranco

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.UnsupportedOperationException
import java.util.concurrent.atomic.AtomicLong

@RestController
class MathController {

    val counter: AtomicLong = AtomicLong()

    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?): Double{
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedOperationException("Please set a numeric value!")
        return converToDouble(numberOne) + converToDouble(numberTwo)
    }

    fun isNumeric(stringNumber: String?): Boolean {
        if (stringNumber.isNullOrBlank()) return false
        val number = stringNumber.replace(",".toRegex(), ".")
        return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }

    fun converToDouble(stringNumber: String?): Double {
        if (stringNumber.isNullOrBlank()) return 0.0

        val number = stringNumber.replace(",".toRegex(), ".")
        return if (isNumeric(number)) number.toDouble() else 0.0
    }
}