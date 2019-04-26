package com.security.chapter03.test;

import com.security.chapter03.properties.SecurityProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestAuto
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/26 8:56
 */
@Data
@Component
public class TestAuto extends AbstractAutoTest{


    public TestAuto() {
        System.out.println(super.securityProperties+"************" + super.getSecurityProperties());
    }
}
