#Build a shadow jar

    gradlew.bat shadowJar

# Aws lambda settings

    org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest

Build aws lambda function

    sam build

Test locally
    
    sam local invoke --log-file=lambda-function.log