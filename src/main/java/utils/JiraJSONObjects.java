package utils;

import org.json.simple.JSONObject;
public class JiraJSONObjects {

    public static String newIssueJSON() {

        JSONObject newIssue = new JSONObject();
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
        fields.put("project", project);
        fields.put("reporter", reporter);
        newIssue.put("fields", fields);
        comment.put("body", "Some comment from Ira");

        return newIssue.toJSONString();
    }

    public static String commentJSON(){
        JSONObject comment = new JSONObject();
//        JSONObject body = new JSONObject();
        comment.put("body", "Some comment from Ira");

        return comment.toString();
    }
}