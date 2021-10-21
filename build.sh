cd userservice
source ./env-variable.sh
mvn clean package
cd ..
cd favouriteservice
source ./env-variable.sh
mvn clean package
cd ..
