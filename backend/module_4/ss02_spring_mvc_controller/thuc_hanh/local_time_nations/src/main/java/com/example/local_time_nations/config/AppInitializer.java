package com.example.local_time_nations.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Khai báo cấu hình tầng Service / Repository / Database (hiện tại chưa dùng nên để null)
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // Đưa cấu hình AppConfig của chúng ta vào tầng Web (Controller / View)
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    // Đặt trạm thu phí: Mọi request ("/") đều sẽ đi qua DispatcherServlet
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
