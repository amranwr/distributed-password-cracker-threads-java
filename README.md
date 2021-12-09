# destributed-password-cracker-threads-java
---------------------------------------------
- a synchronized multi threaded server controling the communication between clients and crackers.
- crackers threads which only have access to the server through password.
- clients threads which only have acces to the server.


server:
- server distribute workload from clients to the crackers.
- server has 4 threads
    - thread for accepting connections form crackers.
    - thread for accepting connections from clients.
    - thread for taking input from clients distrbute it to the available cracker.
    - thread for taking input from crackers distrbute it to the right client.


cracker:
- trying to break the encrypted password.
- each cracker has :
    - a thread for input.
    - thread for output. 
    - thread for cracking the password. 


client:
- want to crack password and gives order through command line.
- each client has :
    - thread for input.
    - thread for output.




