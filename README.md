# GenovianPearConsultation

This is a backend app designed to support a user interface that asks the user a series of questions to see if they
can be prescribed a medication that targets an allergy to the Genovian Pear.

## The API largely follows RESTful principles, with the following endpoints:

- `POST /consultations` - create a new consultation form
- `GET /consultations/{id}` - retrieve the state of a consultation form and see the list of questions and status of the form
- `POST /consultations/{id}/questions/{questionId}/answer` - answer a question
- `POST /consultations/{id}/submit` - submit a consultation form

The client should therefore be able to create a new consultation form, answer questions, submit the form, and then
retrieve the status of the form again to see the status and any rejection reason.

## How to build and run the application:

- Run `mvn package` to build it
- Run `mvn test` to run the automated tests
- Start application with `java -jar target/genovianpearconsultation-1.0-SNAPSHOT.jar server config.yml`

## How to test the API using some sample curl commands:

If the automated tests aren't enough and you want to perform exploratory testing, you can send the following requests
using curl:

### 1. To create a new consultation form
```
curl --location 'http://localhost:8080/consultations' \
--header 'Content-Type: application/json' \
--data '{
    "productName": "genovian-pear"
}'
```

Sample response:

```
{
    "id": 1,
    "productName": "genovian-pear",
    "questions": [
        {
            "id": 1,
            "question": "Please enter your height."
        },
        {
            "id": 2,
            "question": "Please enter your weight."
        },
        {
            "id": 3,
            "question": "Please enter your blood pressure."
        },
        {
            "id": 4,
            "question": "Are you allergic to any of the ingredients in this medication?"
        }
    ],
    "answers": [],
    "status": null,
    "rejectionReason": null
}
```

This lists the questions that must be answered to determine if the user can be prescribed the medication.

The questions are currently hardcoded, but it is envisaged that they will be configurable in the future.

The response also contains the `id` of the form. The remaining curl requests in this README assume that it's 1. If not,
you'll need to adjust the requests accordingly.

### 2. Send four POST requests, one to answer each question:

```
curl --location 'http://localhost:8080/consultations/1/questions/1/answer' \
--header 'Content-Type: application/json' \
--data '{
    "questionId": 1,
    "answer": "187 cm"
}'
```

```
curl --location 'http://localhost:8080/consultations/1/questions/2/answer' \
--header 'Content-Type: application/json' \
--data '{
    "questionId": 2,
    "answer": "85 kg"
}'
```

```
curl --location 'http://localhost:8080/consultations/1/questions/3/answer' \
--header 'Content-Type: application/json' \
--data '{
    "questionId": 3,
    "answer": "120/80"
}
'
```

```
curl --location 'http://localhost:8080/consultations/1/questions/4/answer' \
--header 'Content-Type: application/json' \
--data '{
    "questionId": 4,
    "answer": "Yes"
}
'
```

### 3. Send a GET request to retrieve the state of the consultation. The status should hopefully be "COMPLETED" at this point:
```
curl --location 'http://localhost:8080/consultations/1'
```

Sample response:

```
{
    "id": 1,
    "productName": "genovian-pear",
    "questions": [
        {
            "id": 1,
            "question": "Please enter your height."
        },
        {
            "id": 2,
            "question": "Please enter your weight."
        },
        {
            "id": 3,
            "question": "Please enter your blood pressure."
        },
        {
            "id": 4,
            "question": "Are you allergic to any of the ingredients in this medication?"
        }
    ],
    "answers": [
        {
            "questionId": 1,
            "answer": "187 cm"
        },
        {
            "questionId": 2,
            "answer": "85 kg"
        },
        {
            "questionId": 3,
            "answer": "120/80"
        },
        {
            "questionId": 4,
            "answer": "Yes"
        }
    ],
    "status": "COMPLETED",
    "rejectionReason": null
}
```

### 4. Send a POST request to the `/submit` endpoint to submit the form:
```
curl --location --request POST 'http://localhost:8080/consultations/1/submit'
```

### 5. Send another GET request to get the status of the form:
```
curl --location 'http://localhost:8080/consultations/1'
```

If your request is successful then the status will now be "APPROVED".

If not, the `status` will be "REJECTED" and the `rejectionReason` field will tell you why.

Sample response:
```
{
    "id": 1,
    "productName": "genovian-pear",
    "questions": [
        {
            "id": 1,
            "question": "Please enter your height."
        },
        {
            "id": 2,
            "question": "Please enter your weight."
        },
        {
            "id": 3,
            "question": "Please enter your blood pressure."
        },
        {
            "id": 4,
            "question": "Are you allergic to any of the ingredients in this medication?"
        }
    ],
    "answers": [
        {
            "questionId": 1,
            "answer": "187 cm"
        },
        {
            "questionId": 2,
            "answer": "85 kg"
        },
        {
            "questionId": 3,
            "answer": "120/80"
        },
        {
            "questionId": 4,
            "answer": "Yes"
        }
    ],
    "status": "REJECTED",
    "rejectionReason": "User is allergic to the ingredients"
}
```
