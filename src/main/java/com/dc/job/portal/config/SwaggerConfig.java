/*
* Copyright (c) 2011, Le Travenues Technology Ltd. All Rights Reserved.
*
* The system is designed and developed by Abhibus | www.abhibus.com
*
* AWS Personalization integration software is the confidential and proprietary
* information of Le Travenues Technology Ltd. You shall not disclose such Confidential Information
* and shall use it only in accordance with the terms of the license agreement you entered into with Abhibus.
*/
package com.dc.job.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dc.job.portal"))
                .build()
                .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot REST API",
                "Spring Boot REST API for ERP",
                "1.0",
                "Terms of service",
                "JobPortal",
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}
