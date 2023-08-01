package ru.kryu.dreamkinopoisk.data

import ru.kryu.dreamkinopoisk.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response

}