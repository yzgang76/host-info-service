package com.hpe.zg.spring

import java.net.InetAddress

import com.alibaba.fastjson.JSONObject
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.web.bind.annotation.{RequestMapping, RestController}


@SpringBootApplication
class MyService {}

object MyService {
    def main(args: Array[String]): Unit = {
        new SpringApplicationBuilder(classOf[MyService]).run(args: _*)
    }
}

@RestController
class indexController {
    @RequestMapping(value = Array("/info"))
    def info(): JSONObject = {
        val localhost = InetAddress.getLocalHost
        import scala.jdk.CollectionConverters._
        val json = new JSONObject(System.getenv.asScala.filter(entry=>entry._2 !=null).asJava.asInstanceOf[java.util.Map[String,Object]])
        json.put("hostName", localhost.getHostName)
        json.put("ip", localhost.getHostAddress)
        json.put("canonicalHostName", localhost.getCanonicalHostName)
        json
    }
}