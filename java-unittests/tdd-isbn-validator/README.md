### Stubs & Mocks
 1. We use stubs and mocks when the code we wish to test has some kind of external dependency that we need to override, both
 stubs and mock our own mini implementation of the object that we are overriding. The idea then is that stubs used to be objects
 we turn some kind of data, so that our code can execute.
 
 STUBS		MOCKS
 yes		yes		- Override external dependencies
 yes		no		- Used to test data.
 no			yes		- Used to test behavior
 
 
 ### Fakes & Dummies
 
 These are even lighter weight versions of stubs, the use case for a fake or dummy.
 Is normally when you need to override something but you have no interest in the thing that you are overriding.