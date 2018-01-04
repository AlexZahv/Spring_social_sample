package ru.zahv.alex.springsocial.social

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.social.config.annotation.SocialConfigurer
import org.springframework.social.config.annotation.SocialConfigurerAdapter
import org.springframework.social.connect.Connection
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.UsersConnectionRepository
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository
import org.springframework.social.connect.web.SignInAdapter
import org.springframework.web.context.request.NativeWebRequest
import ru.zahv.alex.springsocial.security.AuthenticationUtil

@Configuration
class SocialConfiguration {

    @Bean
    fun socialConfigurerAdapter(): SocialConfigurer {
        return InMemorySocialConfigurer()
    }

    @Bean
    fun authSignInAdapter(): SignInAdapter {
        return CustomSignInAdapter()
    }
}

class InMemorySocialConfigurer : SocialConfigurerAdapter() {

    override fun getUsersConnectionRepository(connectionFactoryLocator: ConnectionFactoryLocator): UsersConnectionRepository {

        return InMemoryUsersConnectionRepository(connectionFactoryLocator)
    }
}

class CustomSignInAdapter : SignInAdapter {

    override fun signIn(s: String, connection: Connection<*>, nativeWebRequest: NativeWebRequest): String {
        AuthenticationUtil.authenticate(connection)
        return "/home"
    }
}