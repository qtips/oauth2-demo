package no.qadeer.authorizationserver.authentication

object UsernameAndPasswordRepository {
    private val map = mutableMapOf<String, String>()

    init {
        map["testUser"] = "test"
    }

    fun newUser(username: String, password: String) {
        map[username] = password
    }

    fun get(username: String): String? {
        return map[username]
    }

}