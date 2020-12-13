package com.wholedetail.gramophone.network.service

import com.wholedetail.gramophone.account.credentials.UserCredentials
import io.reactivex.rxjava3.core.Single

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
//ToDo undwrap to Repository
class LoginService {


    fun login(credentials: UserCredentials): Single<String> {
//        val result = accountRepository.login(credentials)
//        UserCredentials("email@gmail.com", "Jane Doe")

        return Single.fromSupplier { "testHash" }
        /*if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result*/
    }

    fun logout() {
//        accountRepository.logout()
    }

}