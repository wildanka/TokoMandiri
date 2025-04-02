package com.example.tokomandiri.app.base.data.mapper

interface Mapper<S, D> {
    fun map(source: S): D
}