package no.qadeer.authorizationserver

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/login")
class Login {

    @GET
    @Produces(MediaType.TEXT_HTML)
    fun login(
        @QueryParam("response_type") responseType: String,
        @QueryParam("client_id") clientId: String,
        @QueryParam("redirect_uri") redirectUri: String,
        @QueryParam("scope") scope: String,
        @QueryParam("state") state: String,
    ): Response {
        return Response.ok(
            """
        <!DOCTYPE html>
        <html>
        <body>
        
        <h2>Login Form</h2>
        
        <form action="/login?response_type=$responseType&client_id=$clientId&redirect_uri=$redirectUri&scope=$scope&state=$state" method="post">
          <div class="container">
            <label for="uname"><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="uname" required>
        
            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="psw" required>
                
            <button type="submit">Login</button>
          </div>
        </form>
        
        </body>
        </html>
            
        """,
        ).build()
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    fun doLogin(
        @QueryParam("response_type") responseType: String,
        @QueryParam("client_id") clientId: String,
        @QueryParam("redirect_uri") redirectUri: String,
        @QueryParam("scope") scope: String,
        @QueryParam("state") state: String,
    ): Response {
        // Verify credentials
        // redirect to callback
        return Response.ok(
            """
            
        """,
        ).build()
    }

    private object UsernameAndPasswordRepository {
        private val map = mutableMapOf<String,String>()
        init {
            map["testUser"] = "test"
        }

        fun newUser(username: String, password: String) {
            map[username] = password
        }

    }
}
