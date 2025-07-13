printf "POST grpcurl\n"
printf "\n"

./grpcurl -plaintext localhost:9090 list;
printf "\n"

./grpcurl -plaintext localhost:9090 describe people.PeopleInfo;
printf "\n"

./grpcurl -plaintext -d '{"name":"snsd"}' localhost:9090 people.PeopleInfo/PeopleEmail;

./grpcurl.exe -plaintext -d '{"name":"snsd"}' localhost:9090 people.PeopleInfo/PeopleEmail;

./grpcurl -plaintext -proto greet.proto -d '{"name":"snsd"}' localhost:9090 people.PeopleInfo/PeopleEmail;

./grpcurl -plaintext -proto PeopleInfo.proto -d '{"nationality":"SG"}' localhost:9090 people.PeopleInfo/PeopleEmail;

./grpcurl -plaintext -proto ./proto/PeopleInfo.proto -d '{"nationality":"SG"}' localhost:9090 people.PeopleInfo/PeopleEmail;

./grpcurl -plaintext -proto ./proto/PeopleInfo.proto --proto_path ./proto/ -d '{"nationality":"SG"}' localhost:9090 people.PeopleInfo/PeopleEmail;

./grpcurl -plaintext -proto ./proto/PeopleInfo.proto -I ./proto -d '{"nationality":"SG"}' localhost:9090 people.PeopleInfo/PeopleEmail;

./grpcurl -plaintext -proto ./proto/PeopleInfo.proto --proto_path=./proto -d '{"nationality":"SG"}' localhost:9090 people.PeopleInfo/PeopleEmail;

./grpcurl -plaintext -proto ./proto/PeopleInfo.proto -import-path ./proto -d '{"nationality":"SG"}' localhost:9090 people.PeopleInfo/PeopleEmail;

./grpcurl -plaintext -d '{"nationality":"US"}' localhost:9090 people.PeopleInfo/PeopleEmail;

./grpcurl -plaintext -proto ./proto/PeopleInfo.proto -import-path ./proto -d '{"nationality":"SG"}' localhost:9090 people.PeopleInfo/Nationality;

./grpcurl -plaintext -proto ./proto/PeopleInfo.proto -import-path ./proto -d '{"identificationNumber":"987-56-3201", "passportNumber":"509823641", "nationality":"US"}' localhost:9090 people.PeopleInfo/Person;

./grpcurl -plaintext -proto ./proto/PeopleInfo.proto -import-path ./proto -d '{"nationality":"SG"}' localhost:9090 people.PeopleInfo/PeopleEmail;
