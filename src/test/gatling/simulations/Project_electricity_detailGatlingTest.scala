import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the Project_electricity_detail entity.
 */
class Project_electricity_detailGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://127.0.0.1:8080"""

    val httpConf = http
        .baseURL(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test the Project_electricity_detail entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJSON
        .check(header.get("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(1)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all project_electricity_details")
            .get("/api/project-electricity-details")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new project_electricity_detail")
            .post("/api/project-electricity-details")
            .headers(headers_http_authenticated)
            .body(StringBody("""{"id":null, "projectid":null, "temporaryconnection":null, "load_required":null, "existing_connecton":null, "account_number":"SAMPLE_TEXT", "load_demand":null, "load_demand_kva":null, "new_load_demand_kw":null, "new_load_demand_kva":null, "load_demand_date":"2020-01-01T00:00:00.000Z", "regular_connection":null, "regular_load_required":null, "regular_existing_connection":null, "regular_uhbvn_dhbvn_customer_type":null, "regular_account_number":"SAMPLE_TEXT", "regular_load_if_any_kw":null, "regular_existing_load_kva":null, "regular_new_load_demand_kw":null, "regular_new_load_demand_kva":null, "regular_demand_date":"2020-01-01T00:00:00.000Z"}""")).asJSON
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_project_electricity_detail_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created project_electricity_detail")
                .get("${new_project_electricity_detail_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created project_electricity_detail")
            .delete("${new_project_electricity_detail_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(100) over (1 minutes))
    ).protocols(httpConf)
}
