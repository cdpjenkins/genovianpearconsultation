# TODO
- When all questions are answered, status is set to PENDING
- When in state PENDING, can submit for approval
- Form is approved if <some condition> is met
- Form is rejected if <some condition> is not met
- If form is rejected, a field is set on the form with a reason for rejection
- We're specifying IDs in both URL path and in JSON body, which allows them to be different

- Auth
    - See https://codeburst.io/understanding-authentication-and-authorization-in-dropwizard-app-kotlin-e593d2052f33
    - Authentication: APi requests are only processed if valid basic auth credentials are provided (add comment saying we'd like
      want something stronger than this)
    - Authorisation: Users can only interact with forms that they own
- Error handling:
    - Trying to POST same answer that has already been answered 
    - Trying to GET a form that doesn't exist -> 404
    - Trying to POST to a form that doesn't exist -> 404
    - Trying to POST to a form that is not in state IN_PROGRESS -> 400
    - Trying to submit to a form that is in state PENDING -> 400

# DONE
- Create a basic Dropwizard app skeleton
- POST a ConsultationForm to create it
- GET a ConsultationForm by id
- Consultation Form has questions
- POST textual answers to those questions by ID
- Before all questions are answered, status is set to IN_PROGRESS
