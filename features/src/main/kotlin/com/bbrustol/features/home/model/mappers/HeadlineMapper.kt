package com.bbrustol.features.home.model.mappers

import com.bbrustol.core.data.remote.newsapi.model.response.users.UsersResponseItem
import com.bbrustol.features.home.model.UsersModel

private fun UsersResponseItem.toModel() = UsersModel(
    id = id,
    name = name
)

fun List<UsersResponseItem>.toListUsersModel() = this.map { it.toModel() }
