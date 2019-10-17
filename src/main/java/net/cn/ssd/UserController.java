package net.cn.ssd;

import net.cn.ssd.framework.common.pojo.EasyUiDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("/findByPage")
    public EasyUiDataGridResult findByPage(){
        return  restTemplate.getForObject("http://localhost:8080/item/list", EasyUiDataGridResult.class);
    }

}
