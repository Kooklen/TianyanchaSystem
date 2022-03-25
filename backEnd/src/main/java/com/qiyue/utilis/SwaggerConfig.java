//package com.qiyue.utilis;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
////Profile是方法1（只在dev和test环境下开启）
//@Profile({"dev","test"})
//public class SwaggerConfig implements WebMvcConfigurer {
//    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
//    //这个是方法2哦，使用的话在new Docket里添加.Enable方法将参数放入即可
////    @Value(value = "${swagger.show}")
////    private Boolean swaggerEnabled;
//
//    /**
//     * apiInfo() 增加API相关信息
//     * 所有的注解
//     * .apis(RequestHandlerSelectors.any())
//     * 指定部分注解1.Api.class(@APi),2.ApiOperation.class(@ApiOperation),3.ApiImplicitParam.class(@ApiImplicitParam)
//     *.apis(RequestHandlerSelectors.withMethodAnnotation(Api.class))
//     * 指定包路径
//     * .apis(RequestHandlerSelectors.basePackage("这里填写需要的路径"))
//     * .paths() 这个是包路径下的路径,PathSelectors.any()是包下所有路径
//     */
//    @Bean
//    public Docket createRestApi() {
////        log.info(""+swaggerEnabled);
//        return new Docket(DocumentationType.SWAGGER_2)
//                .useDefaultResponseMessages(false)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                //创建
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("SpringBoot-Swagger2集成")
//                .description("springboot | swagger")
//                // 作者信息
//                .version("0.0.1")
//                .build();
//    }
//
//}
