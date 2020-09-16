import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class AddDeleteComment {

    @Test
    public void addDeleteComment() {
        JSONObject issue = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject reporter = new JSONObject();
        JSONObject issueType = new JSONObject();
        JSONObject project = new JSONObject();
        JSONObject comment = new JSONObject();

        issueType.put("id", "10105");
        issueType.put("name", "test");
        project.put("id", "10508");
        reporter.put("name", "IrynaKapustina");
        fields.put("issuetype", issueType);
        fields.put("summary", "some summary");
        fields.put("reporter", reporter);
        fields.put("project", project);
        issue.put("fields", fields);
        comment.put("body", "Some comment from Ira");


        //Create issue
        ValidatableResponse response = given().
                auth().preemptive().basic("IrynaKapustina", "IrynaKapustina").
                contentType(ContentType.JSON).
                body(issue.toJSONString()).
                when().
                post("https://jira.hillel.it/rest/api/2/issue").
                then().
                statusCode(201).contentType(ContentType.JSON);

        String responseBody = response.extract().asString();
        System.out.println(responseBody);
        String keyIssue = response.extract().path("key");

        //Get issue response
        Response responseCreatedIssue = given().
                auth().preemptive().basic("IrynaKapustina", "IrynaKapustina").
                contentType(ContentType.JSON).
                when().
                get("https://jira.hillel.it/rest/api/2/issue/" + keyIssue).
                then().
                statusCode(200).contentType(ContentType.JSON).
                extract().response();
        responseCreatedIssue.print();

        //Post comment
        Response responsePostComment = given().
                auth().preemptive().basic("IrynaKapustina", "IrynaKapustina").
                contentType(ContentType.JSON).
                body(comment.toJSONString()).
                when().
                post("https://jira.hillel.it/rest/api/2/issue/" + keyIssue + "/comment").
                then().
                statusCode(201).
                extract().response();
        responsePostComment.print();

        //Post comment
        Response responseDeleteComment = given().
                auth().preemptive().basic("IrynaKapustina", "IrynaKapustina").
                contentType(ContentType.JSON).
                body(comment.toJSONString()).
                when().
                delete("https://jira.hillel.it/rest/api/2/issue/" + keyIssue + "/comment").
                then().
                statusCode(201).
                extract().response();
        responsePostComment.print();


    }


}
