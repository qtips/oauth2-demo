package no.qadeer.myapp
                     //TODO se hvordan en faktisk flyt ser ut med oauth2
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import java.net.URLEncoder

private const val CLIENT_ID = "MYAPP"

@Path("/myapp")
class Welcome {
    @GET
    @Path("/welcome")
    @Produces(MediaType.TEXT_HTML)
    fun welcome(): String {
        var redirect = URLEncoder.encode("http://localhost:8081/myapp/callback", Charsets.UTF_8)
        return """
        <!DOCTYPE html>
        <html>
        <body>
         Welcome!   <a href="http://localhost:8080/oauth2/authorize?response_type=code&client_id=$CLIENT_ID&redirect_uri=$redirect&scope=read&state=randomstring">Login</a>
        </body>
        </html>
        """.trimIndent()
    }

    @GET
    @Path("/callback")
    @Produces(MediaType.TEXT_HTML)
    fun callback(
        @QueryParam("code") code: String
    ): String {
        
        return "Success: $code"
    }
}