package com.himanshoe.posts

import com.himanshoe.di.domain.DomainProvider
import com.himanshoe.util.getBodyContent
import com.himanshoe.util.getUserId
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.locations.post


fun Application.postsRoute(domainProvider: DomainProvider) {

    routing {

        get<PostsList> { request ->
            val response = domainProvider.provideGetPostsUseCase().invoke(Pair(request.page, request.count))
            call.respond(response)
        }

        authenticate {

            post<CreatePost> {
                val userId = getUserId()
                val post = getBodyContent<Post>()
                val response = domainProvider.provideCreatePostUseCase().invoke(Pair(userId,post))
                call.respond(response)
            }
        }
    }
}