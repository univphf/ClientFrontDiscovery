package com.dev.ht.clientfrontdiscovery;

import java.util.List;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableEurekaClient

public class ClientfrontdiscoveryApplication {
   
    @Autowired
    private DiscoveryClient discoveryClient;

    
   public String serviceUrl() {
       final StringBuilder sb=new StringBuilder();
        List<ServiceInstance> instance = discoveryClient.getInstances("back");
               instance.forEach( 
    			  si ->
    			   { 
                             sb.append ("Host= ");
                             sb.append(si.getHost()); 
                             sb.append(" Uri= "); 
                             sb.append(si.getUri());
                             sb.append(" Port= ");
                             sb.append(si.getPort());
    			   }
    			);
       System.out.println("sb= "+sb.toString());

        return "taille : "+instance.size()+" "+sb.toString();
    }

    @RequestMapping("/discover")
    public String index() {
        return serviceUrl();
    }

    	public static void main(String[] args) {
		SpringApplication.run(ClientfrontdiscoveryApplication.class, args);
	} 
}
