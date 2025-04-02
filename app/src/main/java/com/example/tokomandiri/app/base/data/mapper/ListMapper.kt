package com.example.tokomandiri.app.base.data.mapper

interface ListMapper<S, D> : Mapper<S, D> {

    fun mapList(source: List<S>): List<D> {
        return source.map { map(it) }
    }
}
