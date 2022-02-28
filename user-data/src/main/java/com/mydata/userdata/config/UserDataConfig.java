package com.mydata.userdata.config;

import com.expediagroup.beans.BeanUtils;
import com.expediagroup.beans.transformer.BeanTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDataConfig {

  /**
   * Bean Transformer Bean for transforming POJO classes
   *
   * @return {@link BeanTransformer}
   */
  @Bean
  public BeanTransformer beanTransformer() {
    return new BeanUtils().getTransformer();
  }
}
