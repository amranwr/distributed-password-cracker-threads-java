# distributed-password-cracker-threads-java
---------------------------------------------
- a synchronized multi threaded server controlling the communication between clients and crackers.
- crackers threads which only have access to the server through password.
- client threads which only have access to the server.


server:
- server distributes workload from clients to the crackers.
- server has 4 threads
    - thread for accepting connections from crackers.
    - thread for accepting connections from clients.
    - thread for taking input from clients and distributing it to the available cracker.
    - thread for taking input from crackers and giving it to the right client.


cracker:
- trying to break the encrypted password.
- each cracker has :
    - a thread for input.
    - thread for output.
    - thread for cracking the password.


client:
- want to crack password and give order through command line.
- each client has :
    - thread for input.
    - thread for output.



