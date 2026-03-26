package com.example.latinlearn

object MathTermRepository {
    fun getMathTerms(): List<MathTerm>{
        return listOf(
            MathTerm(1, "Abscissa", "Абсцисса"),
            MathTerm(2, "Addend", "Слагаемое"),
            MathTerm(3, "Antecedent", "Предшествующий"),
            MathTerm(4, "Bisector", "Биссектриса"),
            MathTerm(5, "Circumference", "Длина окружности"),
            MathTerm(6, "Coefficient", "Коэффициент"),
            MathTerm(7, "Commutative", "Переместительный"),
            MathTerm(8, "Congruent", "Конгруэнтный"),
            MathTerm(9, "Contrapositive", "Контрапозиция"),
            MathTerm(10, "Denominator", "Знаменатель"),
            MathTerm(11, "Digit", "Цифра"),
            MathTerm(12, "Disjoint", "Непересекающиеся"),
            MathTerm(13, "Exponent", "Показатель степени"),
            MathTerm(14, "Extrapolate", "Экстраполировать"),
            MathTerm(15, "Irrational", "Иррациональный")
        )
    }
}