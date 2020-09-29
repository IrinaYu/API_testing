package utils;

public interface APIPathes {

    public static String baseURL(){
        String baseURL = YamlReader.getEnvironment(System.getenv("environment"));
        return baseURL;
    }

    public static String issueURL(){
        String issue = baseURL() + "/rest/api/2/issue/";
        return issue;
    }

    public static String commentURL(){
        String comment = issueURL() + "%s/comment/";
        return comment;
    }

    public static String commentForDeletingURL() {
        String commentForDeleting = issueURL() + "%s/comment/%s/";
        return commentForDeleting;
    }

    String baseURL = "https://jira.hillel.it";
    String issue = baseURL + "/rest/api/2/issue/";
    String comment = issue + "%s/comment/";
    String commentForDeleting = issue + "%s/comment/%s/";
}



