import org.apache.spark._

import org.elasticsearch.spark._  //lib elasticsearch-spark_2.10.-2.10
import scala.collection.mutable.{ListBuffer, LinkedHashMap}
import collection.mutable.Buffer
import java.io._



//Attr1:country code                     ---------give it a value by list "ct"
//Attr2:subject_length                   ---------the length is the value
//Attr3:if there are links in the mail   --------- yes => 1, no => 0
//Attr4:who get this mail                --------- give it a value by list "to"
//Attr5:EncodeType                       --------- give it a value by list "encode"

object HelloWorld {
    def main(args: Array[String]) {

     val query = "{\n    \"query\": {\n\"filtered\": {\n\"query\": {\n\"match_all\": {}\n},\n\"filter\": {\n\"range\": {\n\"LogTime\": {\n\"gte\": \"2015-01-01T00:00:00.000Z\"\n , \"lte\": \"2015-07-01T00:00:00.000Z\"\n                    }\n                }\n            }\n        }\n    }\n}"
      val conf = new SparkConf(true)
        .setAppName("HelloWorld")//set app name
        .setMaster("spark://AhDa-PC:7077")//set spark master
        .set("es.index.auto.create", "true")
        .set("es.nodes", "localhost:9200")//set elasticsearch nodes
        //.set("es.method","GET")//?q=Smith
        .set("es.endpoint","_search")//search
        //.set("es.scroll.keepalive","60m")//keep query result alive for 60min
        .set("es.resource", "logstash-2015.05.18/logs")// form table/type
        //.set("es.resource", "logstash-2015.05.18/_search")
        .set("spark.executor.memory", "6g")//allocate 6g memory for spark
        .set("es.query",query)//input query string
        .setSparkHome( "C:\\Users\\AhDa\\Downloads\\spark-1.1.1-bin-hadoop2.4")//set spark home
        .setJars(Seq("lib/spark-examples-1.1.1-hadoop2.4.0.jar"))

      val sc = new SparkContext(conf)
      sc.addJar("lib/HelloWorld.jar")


      //get esrdd
      val result =  sc.esRDD//logstash-*/*

      //get rdd date map to list
      val data = result.collect.toList
      //list of "to"
      val to = List("bruce@bruce-guenter.dyndns.org","lists-linux-xfs@bruce-guenter.dyndns.org","lists-djb-ezmlm@bruce-guenter.dyndns.org",
      "lists-reiserfs-list@bruce-guenter.dyndns.org","lists-bikini@bruce-guenter.dyndns.org","lists-linux-kernel@bruce-guenter.dyndns.org",
      "cvs@bruce-guenter.dyndns.org","em-ca-bruceg@em.ca","bruce@untroubled.org","rait@bruce-guenter.dyndns.org",
      "lists-ezmlm@bruce-guenter.dyndns.org","webmaster@ezmlm.org","em-ca-bait@em.ca")
      //list of "country code"
      val ct = List(
        "AF","AL","DZ","AS","AD","AO","AI","AQ","AG","AR","AM","AW","AU","AT","AZ","BS","BH","BD","BB","BY",
        "BE","BZ","BJ","BM","BT","BO","BA","BW","BV","BR","IO","BN","BG","BF","BI","KH","CM","CA","CV","KY",
        "CF","TD","CL","CN","CX","CC","CO","KM","CG","CD","CK","CR","CI","HR","CU","CY","CZ","DK","DJ","DM",
        "DO","TP","EC","EG","SV","GQ","ER","EE","ET","FK","FO","FJ","FI","FR","FX","GF","PF","TF","GA","GM",
        "GE","DE","GH","GI","GR","GL","GD","GP","GU","GT","GN","GW","GY","HT","HM","VA","HN","HK","HU","IS",
        "IN","ID","IR","IQ","IE","IL","IT","JM","JP","JO","KZ","KE","KI","KP","KR","KW","KG","LA","LV","LB",
        "LS","LR","LY","LI","LT","LU","MO","MK","MG","MW","MY","MV","ML","MT","MH","MQ","MR","MU","YT","MX",
        "FM","MD","MC","MN","ME","MS","MA","MZ","MM","NA","NR","NP","NL","AN","NC","NZ","NI","NE","NG","NU",
        "NF","MP","NO","OM","PK","PW","PA","PG","PY","PE","PH","PN","PL","PT","PR","QA","RE","RO","RU","RW",
        "KN","LC","VC","WS","SM","ST","SA","SN","RS","SC","SL","SG","SK","SI","SB","SO","ZA","SS","GS","ES",
        "LK","SH","PM","SD","SR","SJ","SZ","SE","CH","SY","TW","TJ","TZ","TH","TG","TK","TO","TT","TN","TR",
        "TM","TC","TV","UG","UA","AE","GB","US","UM","UY","UZ","VU","VE","VN","VG","VI","WF","EH","YE","ZM",
        "ZW")
      //list of "encode"
      val encode =List("7bit","quoted-printable","base64","8bit")

      //open a writer to write a file
      val writer = new PrintWriter(new File("data/result/FiveAttrData15-1.txt" ))

      //deal with all data
      data.foreach{
        mail =>
         mail._2.get("geoip")//get geoip form data and then get country code in it
           match {//deal with nested Map[]
             case Some(i) => { i match{
               case j: LinkedHashMap[Any,Any] => j.get("country_code2") match {
                 case Some(name) =>{
                   //print("country :")
                   name match{
                   //get one country code
                     case l:String =>
                       //print(l)
                       writer.write((ct.indexOf(l).toFloat+1)/ct.length+" ")
                     //if  country country we get is more then one
                     case k: Buffer[Any] =>
                       //print(k(0))
                       writer.write((ct.indexOf(k(0)).toFloat+1)/ct.length+" ")
                   }
                 }
               }
             }
           }
           //if not exist geoip
           case None => {
             //println(" ,"+"Content-Transfer-EncodingLength Not Match")
             writer.write("0"+" ")
           }
         }
          mail._2.get("subj") match {
            case Some(name) => {
              //print(" ,subject:"+name)

              //print(" ,garbled word"+name)
              writer.write(name.toString.length +" ")
            }
            case None => {//print(" ,"+"Subject Not Match")
              writer.write("0" +" ")
            }
          }
          mail._2.get("link") match {
            case Some(name) =>{
              //print(" ,haslink:"+name)
              writer.write("1"+" ")
            }
            case None => {//print(" ,"+"haslink Not Match")
              writer.write("0"+" ")
            }
          }
          mail._2.get("to") match {
            case Some(name) =>{
              print(" ,to:"+name)
              name match{
                case l:String =>
                  //print(l)
                  //
                  writer.write((to.indexOf(l.toString.toLowerCase()).toFloat+1)/to.length +" ")
                case k: Buffer[Any] =>
                  //print(k(0))
                  //if(!to.contains(k(0).toString)) to += k(0).toString
                  writer.write((to.indexOf(k(0).toString.toLowerCase()).toFloat+1)/to.length +" ")
              }
            }
            case None => {
              //print(" ," + "to Not Match")
              writer.write("0"+" ")
            }
          }
          mail._2.get("CTEncode") match {
            case Some(name) =>{
              //println(" ,Content-Transfer-Encoding:")
              name match{
                case l:String =>
                  //print(l)
                  writer.write((encode.indexOf(name.toString.toLowerCase()).toFloat+1)/encode.length +" ")
                case k: Buffer[Any] =>
                  //print(k(0))
                  writer.write((encode.indexOf(k(0).toString.toLowerCase()).toFloat+1)/encode.length +" ")
              }
            }
            case None => {
              //println(" ,"+"Content-Transfer-EncodingLength Not Match")
              writer.write("0"+" ")
            }
          }
          writer.write("\n")
      }

      //close writer
      writer.close()
    }
  }


