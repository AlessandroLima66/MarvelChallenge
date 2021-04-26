package br.com.alessandro.marvelchallenge.util

import br.com.alessandro.marvelchallenge.util.Constants.TEXT_NOT_INFORMED

fun String.getDescription(): String =
    if (this.isEmpty()) TEXT_NOT_INFORMED else this
