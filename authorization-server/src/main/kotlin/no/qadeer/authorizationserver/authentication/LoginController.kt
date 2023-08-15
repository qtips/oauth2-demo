package no.qadeer.authorizationserver.authentication

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/login")
class LoginController {

    @GET
    @Produces(MediaType.TEXT_HTML)
    fun login(
        @QueryParam("response_type") responseType: String?,
        @QueryParam("client_id") clientId: String?,
        @QueryParam("redirect_uri") redirectUri: String?,
        @QueryParam("scope") scope: String?,
        @QueryParam("state") state: String?,
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
            <input type="text" placeholder="Enter Username" name="username" required>
        
            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>
                
            <button type="submit">Login</button>
          </div>
        </form>
       
        <p>Register <a href="/login/new">new<a/> user</p>
       
        </body>
        </html>
            
        """,
        ).build()
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    fun doLogin(
        @QueryParam("response_type") responseType: String?,
        @QueryParam("client_id") clientId: String?,
        @QueryParam("redirect_uri") redirectUri: String?,
        @QueryParam("scope") scope: String?,
        @QueryParam("state") state: String?,
        @FormParam("username") username: String,
        @FormParam("password") password: String
    ): Response {
        // Verify credentials
        if (username.isBlank()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Need Username").build()
        }
        if (password.isBlank()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Need Password").build()
        }
        val pass = UsernameAndPasswordRepository.get(username)
            ?: return Response.status(Response.Status.NOT_FOUND).entity("User $username not found").build()

        if (password != pass) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Wrong password for user $username").build()
        }

        // redirect to callback
            return Response.ok(
                """
                       Success
        """,
            ).build()
    }

    @GET
    @Path("/new")
    @Produces(MediaType.TEXT_HTML)
    fun new(): Response {
        return Response.ok(
            """
             <!DOCTYPE html>
<html>
<head>
  <title>User Registration</title>
</head>
<body>
  <h1>User Registration</h1>
  <form action="/login/new" method="POST">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>
    
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>
    
    <input type="submit" value="Register">
  </form>
</body>
</html>
        """,
        ).build()
    }

    @POST
    @Path("/new")
    fun new(
        @FormParam("username") username: String,
        @FormParam("password") password: String
    ): Response {
        UsernameAndPasswordRepository.newUser(username, password)
        return Response.ok("Success! ").build()
    }
}
