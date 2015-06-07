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
1|0.49478367801007156|69.46200885827749|0.746142344566081|0.3260329753140059|0.42354583734993556
2|0.45826807859398344|261.4727153303849|0.8452774007093752|0.29539176368629966|0.3360073564829006
3|0.4658140771201803|136.84281720415163|0.9029585415059771|0.2961064515892022|0.3463268964846329
4|0.5362872511276842|31.555636285182167|0.7780735513879327|0.39175650836457804|0.4222867177907298
5|0.6069156342666666|8789.666666666666|0.0|0.328205146|0.7333333333333333
6|0.4391429016426853|486.11008883248735|0.8841551849166063|0.2594619909943722|0.31707759245830314
7|0.652477974662921|2266.6629213483147|0.696629213483146|0.46240277078651687|0.34269662921348315

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
