package br.com.alessandro.marvelchallenge.util

fun String.getDescription(): String =
    if (this.isEmpty()) "nao informado" else this
