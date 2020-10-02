package utils;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class JiraAPISteps {

    @Step (value = "createIssue")
    public static Response createIssue() {
        Response response = given().
                auth().preemptive().basic(Credentials.username, Credentials.password).
                contentType(ContentType.JSON).
                body(JiraJSONObjects.newIssueJSON()).
                when().
                post(APIPathes.issueURL()).
                then().
                statusCode(201).contentType(ContentType.JSON).
                extract().response();
        return response;
    }

    @Step (value = "getIssue")
    public static Response getIssue(String keyIssue) {
        Response response = given().
                auth().preemptive().basic(Credentials.username, Credentials.password).
                contentType(ContentType.JSON).
                when().
                get(APIPathes.issueURL() + keyIssue).
                then().
                statusCode(200).contentType(ContentType.JSON).
                extract().response();
        return response;

    }

    @Step (value = "deleteIssue")
    public static Response deleteIssue(String keyIssue) {
        Response response = given().
                auth().preemptive().basic(Credentials.username, Credentials.password).
                contentType(ContentType.JSON).
                when().
                delete(APIPathes.issueURL() + keyIssue).
                then().
                statusCode(204).
                extract().response();
        return response;
    }

    @Step (value = "checkIfIssueDeleted")
    public static Response checkIfIssueDeleted(String keyIssue) {
        Response response = given().
                auth().preemptive().basic(Credentials.username, Credentials.password).
                contentType(ContentType.JSON).
                when().
                get(APIPathes.issueURL() + keyIssue).
                then().
                statusCode(404).
                extract().response();
        return response;
    }

    @Step (value = "postComment")
    public static Response postComment(String keyIssue) {
        Response response = given().
                auth().preemptive().basic(Credentials.username, Credentials.password).
                contentType(ContentType.JSON).
                body(JiraJSONObjects.commentJSON()).
                when().
                post(String.format(APIPathes.commentURL(), keyIssue)).
                then().
                statusCode(201).
                extract().response();
        return response;
    }

    @Step (value = "deleteComment")
    public static Response deleteComment(String keyIssue, String commentId) {
        Response response = given().
                auth().preemptive().basic(Credentials.username, Credentials.password).
                contentType(ContentType.JSON).
                when().
                delete(String.format(APIPathes.commentForDeletingURL(), keyIssue, commentId)).
                then().
                statusCode(204).
                extract().response();
        return response;
    }

    @Step (value = "getIssueForCheckingComment")
    public static String getIssueForCheckingComment(String keyIssue) {
        String response = given().
                auth().preemptive().basic(Credentials.username, Credentials.password).
                contentType(ContentType.JSON).
                when().
                get(APIPathes.issueURL() + keyIssue).
                then().
                statusCode(200).contentType(ContentType.JSON).
                time(lessThan(1000L)).
                extract().response().path("fields.comment.comments").toString();
        return response;
    }
}
