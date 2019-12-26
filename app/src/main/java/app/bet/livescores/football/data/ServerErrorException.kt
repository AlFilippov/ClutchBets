package app.bet.livescores.football.data

import java.io.IOException

class ServerErrorException(
    val errorCode: Int = 0,
    val body: String = "",
    val errorMessage: String = "",
    val isHandleException: Boolean = false
) : IOException("$errorCode: $body")