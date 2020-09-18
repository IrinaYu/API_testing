import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class DeleteIssue {
    @Test
    public void deleteIssue() {

        JSONObject issue = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject reporter = new JSONObject();
        JSONObject issueType = new JSONObject();
        JSONObject project = new JSONObject();

        issueType.put("id", "10105");
        issueType.put("name", "test");
        project.put("id", "10508");
        reporter.put("name", "IrynaKapustina");
        fields.put("issuetype", issueType);
        fields.put("summary", "some summary");
        fields.put("reporter", reporter);
        fields.put("project", project);
        issue.put("fields", fields);

        Response createIssueResponse = given().
                auth().preemptive().basic("IrynaKapustina", "IrynaKapustina").
                contentType(ContentType.JSON).
                body(issue.toJSONString()).
                when().
                post("https://jira.hillel.it/rest/api/2/issue").
                then().
                statusCode(201).contentType(ContentType.JSON).
                extract().response();
        String keyIssue = createIssueResponse.path("key");

        Response deleteIssueResponse =
                given().
                        auth().preemptive().basic("IrynaKapustina", "IrynaKapustina").
                        contentType(ContentType.JSON).
                        when().
                        delete("https://jira.hillel.it/rest/api/2/issue/" + keyIssue).
                        then().
                        statusCode(204).
                        extract().response();
        deleteIssueResponse.print();

        //Get deleted issue
        //Запрошиваем наш тестовый, удаленный тикет и проверяем, что нам вернулся 404 статус код
        Response checkIfIssueDeletedResponse =
                given().
                        auth().preemptive().basic("IrynaKapustina", "IrynaKapustina").
                        contentType(ContentType.JSON).
                        when().
                        get("https://jira.hillel.it/rest/api/2/issue/" + keyIssue).
                        then().
                        statusCode(404).
                        extract().response();

    }
}
