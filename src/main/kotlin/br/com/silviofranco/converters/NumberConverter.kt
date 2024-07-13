package br.com.silviofranco.converters

object NumberConverter {
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