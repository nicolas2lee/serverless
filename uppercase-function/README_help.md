#Build a shadow jar

    gradlew.bat shadowJar

#Setting handler

    org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest

#Using SAM
The general user experience with sam is not very good

Build aws lambda function

    sam build

Test locally
    
    sam local invoke --log-file=lambda-function.log

##Using aws lambda

Create lambda role

Create lambda role based on aws IAM web interface
https://docs.aws.amazon.com/lambda/latest/dg/lambda-intro-execution-role.html

Or by command

    aws iam create-role --role-name lambda-ex --assume-role-policy-document file://trust-policy.json

Create function, for spring cloud function we should require memory for 512, by default it will have 128M, then you have OutOfMemory Error

    aws lambda create-function \
    --function-name uppercase-function \
    --runtime java11 \
    --role arn:aws:iam::271780907694:role/lambda-role \
    --handler org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest \
    --memory-size 512 \
    --zip-file fileb://build/libs/UppercaseFunction-0.0.1-SNAPSHOT-all.jar  

Delete function

    aws lambda delete-function --function-name "uppercase-function"
