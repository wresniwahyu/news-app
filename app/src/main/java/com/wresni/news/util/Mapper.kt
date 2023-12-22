package com.wresni.news.util

interface Mapper<I, O> {
    fun map(input: I): O
}