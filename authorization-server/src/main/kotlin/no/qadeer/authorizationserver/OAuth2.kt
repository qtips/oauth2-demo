package no.qadeer.authorizationserver

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import java.net.URI

@Path("/oauth2")
class OAuth2 {
    @GET
    @Path("/authorize")
    @Produces(MediaType.TEXT_HTML)
    fun authorize(
        @QueryParam("response_type") responseType: String,
        @QueryParam("client_id") clientId: String,
        @QueryParam("redirect_uri") redirectUri: String,
        @QueryParam("scope") scope: String,
        @QueryParam("state") state: String,
    ): Response {
        //check if already logged in?
        return Response.seeOther(URI.create("login?response_type=$responseType&client_id=$clientId&redirect_uri=$redirectUri&scope=$scope&state=$state")).build()
    }



    @POST
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    fun token(
        @QueryParam("grant_type") grantType: String,
        @QueryParam("code") code: String,
        @QueryParam("client_id") clientId: String,
        @QueryParam("client_secret") clientSecret: String,
        @QueryParam("redirect_uri") scope: String,
    ): String {
        return "Jersey Jetty example."
    }


}