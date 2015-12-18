# EJB-Implementation
Exploring EJB Concept in java
- Session Beans - stateless and statefull
- Entity Beans
- Message Driven Beans

The distributed application consists of 4 servers implementing Session, Entity and Message Driven Bean concepts using University as domain.

The Application has :
1. Session_StatelessBean Server which uses stateless bean concept. And acts like Server for CS441EJBClient1 and Client for Session_StatefulBean Server.
2. Session_StatefulBean Server which uses stateful bean concept and acts as Server for Session_StatelessBean and Client for Professor(Entity Bean) Server.
3. Professor(Entity Bean) Server which uses persistence entity bean concept and acts as Server for Session_StatefulBean and CS441EJBClient1.
4. MsgBean Server which uses concept Message Driven bean of EJB and is used to send the messages.
5. TestAccessTimeout server to test the access timeout of the application if  there are parallel invocations occurring. If there is a concurrent access to a single method and if there is no lock acquired within a time period then concurrency Access timeout exception occurs in the application.

