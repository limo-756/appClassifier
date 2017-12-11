CC=javac
CFLAGS=-g
hello: comparePermission.java removeDuplicate.java
	$(CC) $(CFLAGS) comparePermission.java
	$(CC) $(CFLAGS) removeDuplicate.java
clean:
	$(RM) *.class