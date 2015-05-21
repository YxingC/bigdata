# Logstash Configuraton

<br/>
We first use `grok` to parse attributes every line
And then use `date` to transfer LogTime to date type
Finally  use `multiline` to merge parsed lines into one through pattern `Delivered-To:` which is the begining of every mail
<br/>

### Attributes Parse From Mail
 1|2|3|4|5
:---:|:---------:|:----:|:------:|:---------:
IP | Subject | To | From | Charset 
**6**|**7**|**8**|**9**|**10**
LogTime|Content-Length  |Content-Transfer-Encoding |Http-Links |GeoIP

### Patterns
We use extra patterns and define it in ptn if you want to run mail.conf just put ptn file in logstash/patterns
<br/>


