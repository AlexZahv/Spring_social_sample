package ru.zahv.alex.springsocial

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SpringSocialApplication

fun main(args: Array<String>) {
    SpringApplication.run(SpringSocialApplication::class.java, *args)
}
