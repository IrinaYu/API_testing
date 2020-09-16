import org.json.simple.JSONObject;

public class Main2 {
    public static void main(String[] args) {
        JSONObject login = new JSONObject();
        login.put ("username", "webinar5");
        login.put ("password", "webinar5");

//        System.out.println(login.toJSONString());

        JSONObject issue = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject reporter = new JSONObject();
        JSONObject issueType = new JSONObject();
        JSONObject project = new JSONObject();

        issueType.put("id", "10105");
        issueType.put("name", "test");
        project.put("id", "10508");
        reporter.put("name", "IrynaKapustina");
        fields.put ("issuetype", issueType);
        fields.put ("summary", "some summary");
        fields.put("reporter", reporter);
        issue.put ("fields", fields);

        System.out.println(issue.toString());
    }
}
