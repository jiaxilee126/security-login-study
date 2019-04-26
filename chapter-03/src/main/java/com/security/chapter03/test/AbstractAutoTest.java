package com.security.chapter03.test;

import com.security.chapter03.properties.SecurityProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName AbstractAutoTest
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/26 9:28
 */
@Data
public class AbstractAutoTest {

    @Autowired
    protected SecurityProperties securityProperties;
}
