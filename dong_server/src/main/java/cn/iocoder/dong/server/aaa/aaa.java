package cn.iocoder.dong.server.aaa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ccc")
public class aaa {

    @GetMapping("/bb")
    public void bbb(){
        System.out.println("hhhh");
    }
}
