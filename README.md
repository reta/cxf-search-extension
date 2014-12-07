Apache CXF Search Extension with OData 2.0 demo
==============

- curl http://localhost:8080/rest/api/people -X POST -d "firstName=Tom&lastName=Knocker&age=16"
- curl http://localhost:8080/rest/api/people
- curl -X GET -G http://localhost:8080/rest/api/people/search --data-urlencode $filter="firstName eq 'T*' and age ge 16" 
