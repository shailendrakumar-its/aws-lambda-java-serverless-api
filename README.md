<!--
title: 'AWS Lambda Java Serverless API RSASignature'
description: 'This example demonstrates how to setup a simple HTTP Post endpoint using Java. Once you ping it with post request, it will reply with the signature key.'
layout: Doc
framework: v1
platform: AWS
language: Java
authorLink: 'https://github.com/shailendrakumar-its'
authorName: 'Shailendra Kumar'
-->
# AWS Lambda Java Serverless API RSASignature Example

This example demonstrates how to setup a simple HTTP Post endpoint using Java. Once you ping it with post request, it will reply with the signature key.

Post Request Json : 

{
  "content": "createTime=1582812617&goodName=Flight&goodType=EaseMyTrip&orderAmount=100&orderDesc=Flight Booking&outOrderId=EMTID19022020110404&partnerId=10000025&returnUrl=http://localhost:57774/WebSite1/cardresponse.aspx",
  "privateKey": "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMRCJsNIN4BaehG4YfqzXV5MvLCIied5T/4PINH6xnBs66Mpjlb5hhudxwpWvknvVoZOMFdh+/8GVvuevWjPSiqVwusfg1Wo+09cjNreUPuEu2r8VA5hF3Mye+DzVH2JC1lDL1d/8Kau8xSdWmRjxzlfZiESClXoBPMX165+Nxg9AgMBAAECgYEAuO4EjJquAbo/sBbn7E27OzJf0pC2MXF0WNd0e6gr+KAJ4fM0duwk3Dzt4uYd5JksFN30W0KvK32T1QGdRPeez57ye8cNUwSlxDU8dUtK4Ya5+uq9gkjlCiQ/QhsTHYeRKgAfqRCqjO1cYAEBdgzsZadL6Rz1U93z4iDv8PCOi4ECQQDqwSexzwlWZabMSIC0IA+04bPmjwyMzq2wEJaBj0LRnUIYAU2TIHld6VWysDMHBAjvvmwLyRVJFixb10iDkw2RAkEA1gUcJMwcByI0CcjsJzBlOfOxNkjx+8kDBeZPhGDqHgT+/sQ9vgEHb35gmevek1TqwpukX2GCVoU/pXdoRgp57QJAdhIEJvgcmCm0RNbKdM4TWESUuQeFpmRlE0KkkDE/yDGyBmTRYYhZwpQTGDZq08KBxCMgMKVIYWQXl3Gl9RdHIQJBAJO4lTNk2pel6Qsz7qQiCEyWwqN4d+XVWcvRquLxTccIpcTNSNyDs1EmhqDXKBrDSwKmES9wi1kSwdqA760ggAUCQFtYtm6IC4LBk85nT7ePQdwZNgxU7fYSHhjKV0W353qwvJWO3ftb1xXkmHGc/kG6fR0o9QBdpv6AP5O33DfbL+s=",
  "publicKey": "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEQibDSDeAWnoRuGH6s11eTLywiInneU/+DyDR+sZwbOujKY5W+YYbnccKVr5J71aGTjBXYfv/Blb7nr1oz0oqlcLrH4NVqPtPXIza3lD7hLtq/FQOYRdzMnvg81R9iQtZQy9Xf/CmrvMUnVpkY8c5X2YhEgpV6ATzF9eufjcYPQIDAQAB"
}

## Use Cases

- Wrapping an existing internal or external endpoint/service

## Build

It is required to build prior to deploying. You can build the deployment artifact using Gradle or Maven.

### Gradle

In order to build using Gradle simply run

```bash
gradle wrapper # to build the gradle wrapper jar
./gradlew build # to build the application jar
```

The expected result should be similar to:

```bash
Starting a Gradle Daemon, 1 incompatible Daemon could not be reused, use --status for details
:compileJava
:processResources
:classes
:jar
:assemble
:buildZip
:compileTestJava UP-TO-DATE
:processTestResources UP-TO-DATE
:testClasses UP-TO-DATE
:test UP-TO-DATE
:check UP-TO-DATE
:build

BUILD SUCCESSFUL

Total time:  2.607 s
```

### Maven

In order to build using Maven simply run

```bash
mvn package
```

Note: you can install Maven with

1. [sdkman](http://sdkman.io/) using `sdk install maven` (yes, use as default)
2. `sudo apt-get install mvn`
3. `brew install maven`

If you use Maven to build, then in `serverless.yml` you have to replace

```yaml
package:
  artifact: build/distributions/aws-lambda-java-serverless-api.zip
```
with
```yaml
package:
  artifact: target/aws-lambda-java-serverless-api.jar
```
before deploying.

## Deploy

After having built the deployment artifact using Gradle or Maven as described above you can deploy by simply running

```bash
serverless deploy
```

The expected result should be similar to:

```bash
Serverless: Creating Stack...
Serverless: Checking Stack create progress...
.....
Serverless: Stack create finished...
Serverless: Uploading CloudFormation file to S3...
Serverless: Uploading service .zip file to S3...
Serverless: Updating Stack...
Serverless: Checking Stack update progress...
..............................
Serverless: Stack update finished...
Service Information
service: aws-lambda-java-serverless-api
stage: dev
region: us-east-1
api keys:
  None
endpoints:
  Post - https://XXXXXXX.execute-api.us-east-1.amazonaws.com/pro/rsakeygen
functions:
  aws-lambda-java-serverless-api-rsakeygen: arn:aws:lambda:us-east-1:XXXXXXX:function:aws-lambda-java-serverless-api-rsakeygen

```

## Usage

You can now invoke the Lambda function directly and even see the resulting log via

```bash
serverless invoke --function currentTime --log
```

The expected result should be similar to:

```bash
{
    "statusCode": 200,
    "body": "{\"status\":true,\"signature\":\"VFQjX6R0qVQcP-1TUgCDtf3uix_eTgeS7kFnJhhhVrwIlYa3B8kGNgxOo8H8e7YRTjas7bGORju3lDfckrLxDx8wX5Px0XbWi8xn6pNZMxWdzvZONSR-hjCP9bhfIdbotEAZCAQln9EFtC0zYSo_weVQbqTDbCbsq0W4jrAhXC4.\",\"verify\":true,\"error\":null}",
    "headers": {
        "X-Powered-By": "AWS Lambda & Serverless",
        "Content-Type": "application/json"
    }
}

## Scaling

By default, AWS Lambda limits the total concurrent executions across all functions within a given region to 100. The default limit is a safety limit that protects you from costs due to potential runaway or recursive functions during initial development and testing. To increase this limit above the default, follow the steps in [To request a limit increase for concurrent executions](http://docs.aws.amazon.com/lambda/latest/dg/concurrent-executions.html#increase-concurrent-executions-limit).
