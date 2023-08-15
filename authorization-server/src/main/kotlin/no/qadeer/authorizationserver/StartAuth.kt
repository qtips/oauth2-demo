package no.qadeer.authorizationserver

import org.glassfish.jersey.jetty.JettyHttpContainerFactory
import org.glassfish.jersey.server.ResourceConfig
import java.net.URI

fun main(args: Array<String>) {
  val config = ResourceConfig().packages("no.qadeer.authorizationserver")

  val server = JettyHttpContainerFactory.createServer(URI.create("http://localhost:8080/"), config)

  Runtime.getRuntime().addShutdownHook(Thread {
    try {
      println("Shutting down the application...")
      server.stop()
      println("Done, exit.")
    } catch (_: Exception) {
    }
  })

  Thread.currentThread().join()
}