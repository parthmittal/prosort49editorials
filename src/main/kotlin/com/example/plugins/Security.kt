package com.example.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*

fun Application.configureSecurity() {
    
    authentication {
        jwt {
            val jwtAudience = this@configureSecurity.environment.config.property("jwt.audience").getString()
            val jwtSecret = this@configureSecurity.environment.config.property("jwt.secret").getString()
            val jwtIssuer = this@configureSecurity.environment.config.property("jwt.domain").getString()
            realm = this@configureSecurity.environment.config.property("jwt.realm").getString()
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtIssuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
        }
//        jwt {
//            secret = "secret"
//            issuer = "http://0.0.0.0:8080/"
//            audience = "http://0.0.0.0:8080/hello"
//            realm = "Access to 'hello'"
//        }
    }
}
