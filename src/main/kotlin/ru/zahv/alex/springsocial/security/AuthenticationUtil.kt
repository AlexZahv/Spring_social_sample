package ru.zahv.alex.springsocial.security

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.social.connect.Connection

object AuthenticationUtil {
    private val log = LoggerFactory.getLogger(AuthenticationUtil::class.java)

    fun authenticate(connection: Connection<*>) {
        val userProfile = connection.fetchUserProfile()
        val username = userProfile.username
        val authentication = UsernamePasswordAuthenticationToken(username, null, null)
        SecurityContextHolder.getContext().authentication = authentication
        log.info("User {} {} connected.", userProfile.firstName, userProfile.lastName)
    }
}