# Random_IT_Scenario_Generator-Project(JAVA)
## Overview

This is a simple Java REST API that generates random IT-related scenarios based on user inputs.
You provide:

Technology (e.g., Cloud Computing, Cybersecurity, DevOps)
Role (e.g., System Administrator, Software Engineer, Security Analyst)
Environment (e.g., Enterprise Network, Cloud Infrastructure, On-Prem Data Center)
The API will return:

Your original inputs
A random challenge, incident, and troubleshooting step from a predefined dataset

## How It Works

Input: You send a POST request with JSON body containing:

`{
"technology": "Java",
"role": "Backend Developer",
"environment": "On-Premise"
}`

Output: It will return Input and Randomly generated scenario which include 
 A challenge
 An Incident
 A troubleshootingstep

eg:`{
"technology": "cloud",
"role": "Software Engineer",
"environment": "Cloud Infrastructure",
"scenario": {
    "challenge": "latency issues under high load",
    "incident": "Monitoring alerts triggered",
    "troubleshootingStep": "Check system logs"
    }
}`

## How to Test it

### 1. Start the Server
Run Main.java in IntelliJ,

This starts the server on http://localhost:8080.

### 2. Testing
Use Postman, curl, or any HTTP client
   Send POST requests to http://localhost:8080/scenario

#### postman- 

Please use attached postman collection to test(Postman Collection for Testing)

#### Curl:- 
`curl -X POST http://localhost:8080/scenario   -H "Content-Type: application/json"   -d '{"technology":"Java", "role":"Backend Developer", "environment":"On-Premise"}'
`

