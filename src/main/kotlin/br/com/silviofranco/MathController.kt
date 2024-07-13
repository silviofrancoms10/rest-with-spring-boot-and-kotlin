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

    @RequestMapping(value = ["/sub/{numberOne}/{numberTwo}"])
    fun sub(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?): Double{
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedOperationException("Please set a numeric value!")
        return converToDouble(numberOne) - converToDouble(numberTwo)
    }
    @RequestMapping(value = ["/mul/{numberOne}/{numberTwo}"])
    fun mul(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?): Double{
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedOperationException("Please set a numeric value!")
        return converToDouble(numberOne) * converToDouble(numberTwo)
    }

    @RequestMapping(value = ["/div/{numberOne}/{numberTwo}"])
    fun div(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?): Double{
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedOperationException("Please set a numeric value!")
        return converToDouble(numberOne) / converToDouble(numberTwo)
    }

    @RequestMapping(value = ["/avg/{numberOne}/{numberTwo}"])
    fun avg(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?): Double{
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedOperationException("Please set a numeric value!")
        return (converToDouble(numberOne) + converToDouble(numberTwo)) / 2
    }

    @RequestMapping(value = ["/sqrt/{number}"])
    fun sqrt(@PathVariable(value = "number") number: String?): Double{
        if (!isNumeric(number)) throw UnsupportedOperationException("Please set a numeric value!")
        return Math.sqrt(converToDouble(number))
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