package ru.zahv.alex.springsocial.resources

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.UsersConnectionRepository
import org.springframework.social.connect.web.ProviderSignInUtils
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.context.request.WebRequest
import ru.zahv.alex.springsocial.security.AuthenticationUtil

@Controller
class SignUpController
@Autowired
constructor(connectionFactoryLocator: ConnectionFactoryLocator, connectionRepository: UsersConnectionRepository) {
    private val signInUtils: ProviderSignInUtils

    init {
        signInUtils = ProviderSignInUtils(connectionFactoryLocator, connectionRepository)
    }

    @RequestMapping(value = "/signup")
    fun signUp(request: WebRequest): String {
        val connection = signInUtils.getConnectionFromSession(request)
        if (connection != null) {
            AuthenticationUtil.authenticate(connection)
            signInUtils.doPostSignUp(connection.displayName, request)
        }
        return "redirect:/"
    }
}