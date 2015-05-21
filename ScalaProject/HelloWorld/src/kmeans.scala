import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import scala.math.random
import org.apache.spark._
import SparkContext._
object kmeans {
  def main(args: Array[String]) {
                                //-- spark master        --set app name
    val spark = new SparkContext("spark://AhDa-PC:7077", "k-means",
    //--set spark home
      "C:\\Users\\AhDa\\Downloads\\spark-1.1.1-bin-hadoop2.4", Seq("lib/spark-examples-1.1.1-hadoop2.4.0.jar"))
    spark.addJar("lib/HelloWorld.jar")

    // Load and parse the data
    val data = spark.textFile("data/mllib/FiveAttr_KmeansData.txt")
    val parsedData = data.map(s => Vectors.dense(s.split(' ').map(_.toDouble))).cache()
    
    // Cluster the data into two classes using KMeans
    val numClusters = 5
    val numIterations = 20
    val clusters = KMeans.train(parsedData, numClusters, numIterations)
    //val center = clusters.clusterCenters //cluster Center

    //get the clustered data
    val labels=clusters.predict(parsedData)
    //save the result and collapse into one file
    labels.coalesce(1,true).saveAsTextFile("data/result/kmeansOut5")

    // Evaluate clustering by computing Within Set Sum of Squared Errors
    //val WSSSE = clusters.computeCost(parsedData)
    //println("Within Set Sum of Squared Errors = " + WSSSE)
  }
}
