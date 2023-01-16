echo "Compiling..."
mvn compile
echo "Running..."
mvn exec:java -Dexec.mainClass=org.flashcards.Main