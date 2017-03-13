import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the Projectdetail entity.
 */
class ProjectdetailGatlingTest extends Simulation {

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

    val scn = scenario("Test the Projectdetail entity")
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
            exec(http("Get all projectdetails")
            .get("/api/projectdetails")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new projectdetail")
            .post("/api/projectdetails")
            .headers(headers_http_authenticated)
            .body(StringBody("""{"id":null, "siteaddress":"SAMPLE_TEXT", "district":null, "block":null, "city_town_village":null, "tehsil_subtehsil":null, "multyvillageinvolved":null, "villageinvolved":"SAMPLE_TEXT", "falls_in_aravalli":null, "landprocured":null, "allotedbyhsiidc":null, "estate":"SAMPLE_TEXT", "cluster":"SAMPLE_TEXT", "phase":"SAMPLE_TEXT", "sector":"SAMPLE_TEXT", "plotno":"SAMPLE_TEXT", "hadbastno":"SAMPLE_TEXT", "khasradocument":null, "lies_under_mc":null, "distance_from_mc":"0", "located_in_urban":null, "revenue_shajra_document":null, "jamabandi_document":null, "nonencumbrancecertificate":null, "totalproposedprojectarea":null, "proposed_build_up_area":null, "secotr":null, "projectpurpose":null, "size_of_industry":null, "project_type":null, "nic_code":"SAMPLE_TEXT", "category_of_project":null, "collaboration_with_foreign":null, "detail_project_report":null, "existing_regulatory_approval":null, "approval_application_form":null, "approval_document":null, "edc_sif_clu_fee_paid_applicable":null, "edc_sif_clu_fee_document":null, "propose_employeement":"0"}""")).asJSON
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_projectdetail_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created projectdetail")
                .get("${new_projectdetail_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created projectdetail")
            .delete("${new_projectdetail_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(100) over (1 minutes))
    ).protocols(httpConf)
}
