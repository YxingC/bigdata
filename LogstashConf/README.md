# Logstash Configuraton

### Attributes Parse From Mail
 1|2|3|4|5
:---:|:---------:|:----:|:------:|:---------:
IP | Subject | To | From | Charset 
**6**|**7**|**8**|**9**|**10**
LogTime|Content-Length  |Content-Transfer-Encoding |Http-Links |GeoIP

### Patterns
We use extra patterns and defined it in ptn if you want to run mail.conf just put ptn file in logstash/patterns
