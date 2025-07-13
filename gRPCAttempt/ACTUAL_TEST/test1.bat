time ./grpcurl -plaintext -proto ./proto/PeopleInfo.proto -import-path ./proto -d '{"nationality":"SG"}' localhost:9090 people.PeopleInfo/Nationality;
time ./grpcurl -plaintext -proto ./proto/PeopleInfo.proto -import-path ./proto -d '{"nationality":"US"}' localhost:9090 people.PeopleInfo/Nationality;
