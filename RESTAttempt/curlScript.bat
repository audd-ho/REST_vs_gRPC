printf "GET\n"

curl -X GET http://localhost:8080/People/PeopleInfo/US;
printf "\n"
printf "\n"

curl -X GET http://localhost:8080/People/PeopleInfo/SG;
printf "\n"
printf "\n"

curl -X GET http://localhost:8080/People/PeopleInfo/MY;
printf "\n"
printf "\n"

printf "POST\n"

curl -X POST http://localhost:8080/People/PeopleInfo/Nationality \
-H "Content-Type: application/json" \
-d "US";
printf "\n"
printf "\n"

curl -X POST http://localhost:8080/People/PeopleInfo/Nationality \
-H "Content-Type: application/json" \
-d "SG";
printf "\n"
printf "\n"

curl -X POST http://localhost:8080/People/PeopleInfo/Nationality \
-H "Content-Type: application/json" \
-d "MY";
printf "\n"
printf "\n"

curl -X POST http://localhost:8080/People/PeopleInfo/PeopleEmail \
-H "Content-Type: application/json" \
-d '"SG"';
printf "\n"
printf "\n"

curl -X POST http://localhost:8080/People/PeopleInfo/Person \
-H "Content-Type: application/json" \
-d '{"identificationNumber":"987-56-3201", "passportNumber":"509823641", "nationality":"US"}';
printf "\n"
printf "\n"