package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static common.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class RequestResponseSpecs {

    public static final RequestSpecification defaultLoggingRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);

    public static RequestSpecification authorisedRequestLoggingSpec(String token) {
        return with()
                .filter(withCustomTemplates())
                .log().all()
                .header("Authorization", token)
                .contentType(JSON);
    }

    public static final ResponseSpecification successful200ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();
    public static final ResponseSpecification error500ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(500)
            .log(ALL)
            .build();

    public static final ResponseSpecification error405ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(405)
            .log(ALL)
            .build();

    public static final ResponseSpecification error400ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(ALL)
            .build();
}
