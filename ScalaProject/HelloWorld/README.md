# ScalaProject

###Five Attribute
 1|2|3|4|5
-------------|----------------|-------------|-----------|------------
Country_code | Subject_length | Link_or_not | Addressee | EncodeType                       

###Result
*  <br/>
 *  **data.rar** contains the `k-means input data` and the `result` after clustering
 * We have result of k=2~5
<br/>
<br/>

###Run

##### 1.To run this project, you need to put the following .jar file in lib folder
- spark-assembly-1.1.1-hadoop2.4.0.jar
- spark-examples-1.1.1-hadoop2.4.0.jar

##### 2.Then build this project's jar file to lib folder and named it as
- HelloWorld.jar

##### 3.To run k-means.scala, extract `data.rar` as it's input and set your own `sparkhome` and `master`
##### 4.To run HelloWorld.scala, you should reset all `conf` of SparkConf
