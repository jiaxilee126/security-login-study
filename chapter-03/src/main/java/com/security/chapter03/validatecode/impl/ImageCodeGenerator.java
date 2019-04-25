package com.security.chapter03.validatecode.impl;

import com.security.chapter03.dto.ImageCode;
import com.security.chapter03.validatecode.ValidateCodeGenerator;
import com.security.chapter03.properties.SecurityProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @ClassName ImageCodeGenerator
 * @Description 图片验证码的实现
 * @Auth JussiLee
 * @Date 2019/4/24 10:58
 */
@Data
public class ImageCodeGenerator implements ValidateCodeGenerator{
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(ServletWebRequest request) {
        int width = ServletRequestUtils.getIntParameter(request.getRequest(),"width",
                securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
                securityProperties.getCode().getImage().getHeight());

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200,250));
        g.fillRect(0,0,width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20 ));
        for(int i = 0 ;i < 155 ; i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y , x+x1, y+y1);
        }
        String sRand = "";
        for (int i = 0; i < securityProperties.getCode().getImage().getLength();i++){
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20+ random.nextInt(110), 20 + random.nextInt(110),20 + random.nextInt(110)));
            g.drawString(rand, 13*i+4, 16);
        }
        g.dispose();
        return new ImageCode(image,sRand,60);
    }

    /**
     *  随机背景条纹色
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc , int bc){
        Random random = new Random();
        if( fc > 255){
            fc = fc%255;
        }
        if(bc > 255 ) bc = bc%255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);

        return new Color(r, g, b);
    }

}
