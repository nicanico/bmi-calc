package br.senai.sp.jandira.bmicalc.calcs

import kotlin.math.pow

fun bmiCalculate(weight: Double, height: Double): Double{

    return weight / (height / 100).pow(2)
}