# ScalaProject
1. We extract five attribute form elasticsearch in HelloWorld.scala  </br>
2. Then run k-means in K-means.scala </br>

###Five Attribute
 1|2|3|4|5
-------------|----------------|-------------|-----------|------------
Country_code(242) | Subject_length(9999) | Link_or_not(2) | Addressee(14) | EncodeType(5)                       

###Result
![alt tag](https://github.com/YxingC/bigdata/blob/master/Photos/k-graph.PNG)

We choose the minum errors that __k=7__

ClusterCenter |Country_code | Subject_length | Link_or_not | Addressee | EncodeType
--------|-------------|----------------|-------------|-----------|------------
1|205.3956262518717|64.06269027681235|1.0|4.065618787053342|0.868081899911656
2|132.4770378475383|65.42323709077212|1.0|1.434611738376695|3.930087191722834
3|112.3843489148971|80.3948166252949 |0.0|5.400262687863815|3.632750382443483
4|94.05949630381706|87.14104403842357|1.0|6.912758632082664|0.9394441799460424
5|146.5512792145858|59.87628107442986|0.0|9.357152934002358|3.892239541515546
6|95.51570744606554|91.46189117735488|0.0|2.107406694267628|0.8935279384355756
7|128.0702116274502|54.4622229980455 |0.0|5.706774786124595|1.222795381113872

*  <br/>
 *  **data.rar** contains the `k-means input data` and the `result k=7` after clustering
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
