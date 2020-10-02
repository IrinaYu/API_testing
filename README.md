# AQA9_API
Для запуска теста из консоли необходимо:
В APIPathes  в String baseURL(){
                     String baseURL = YamlReader.getEnvironment(System.getenv("environment"));
                     return baseURL;
                 }
System.getenv необходимо сменить на System.getProperty.
В консоли необходимо запустить mvn clean test -Denvironment=staging      