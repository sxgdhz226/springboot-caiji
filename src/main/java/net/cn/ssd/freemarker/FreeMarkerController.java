package net.cn.ssd.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class FreeMarkerController {

    @RequestMapping("FreeMarkerController")
    public String index(Map<String,Object> result){
        result.put("name","张三");
        result.put("sex","2");
        return "index";
    }
}
