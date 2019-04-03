package com.naswork.erp.utils.framework.base;

import com.naswork.erp.utils.framework.enums.EnvType;
import com.naswork.erp.utils.framework.util.DateUtil;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @Program: Sys
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-11 09:55:03
 **/

public class Sys {

    private static final Logger logger = LoggerFactory.getLogger(Sys.class);

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static EnvType CURRENT_ENV;

    private static ApplicationContext applicationContext;

    public static String APPLICATION_NAME;

    public static Long STARTUP_DATE;

    public static boolean SYSTEM_START_UP_CONFIRM;

    public static boolean SYSTEM_ENUM_CHECT_CONFIRM;

    public Sys(){

    }

    public static void setEnv(ApplicationContext applicationContext){
        setApplicationContext(applicationContext);
        Environment env = applicationContext.getEnvironment();
        String[] activeProfiles = env.getActiveProfiles();
        String[] var3 = activeProfiles;
        int var4 = activeProfiles.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String profile = var3[var5];
            profile = profile.toUpperCase();
            EnvType envType = EnvType.DEV;
            if (EnumUtils.isValidEnum(EnvType.class, profile)) {
                envType = EnvType.valueOf(profile);
            } else {
                if (profile.contains(EnvType.PROD.toString())) {
                    envType = EnvType.PROD;
                }

                if (profile.contains(EnvType.UAT.toString())) {
                    envType = EnvType.UAT;
                }

                if (profile.contains(EnvType.SIT.toString())) {
                    envType = EnvType.PROD;
                }

                if (profile.contains(EnvType.DEV.toString())) {
                    envType = EnvType.DEV;
                }
            }

            CURRENT_ENV = envType;
            logger.info("===================>  System Env is changed to {}", CURRENT_ENV);
        }

        Long startupDate = applicationContext.getStartupDate();
        STARTUP_DATE = startupDate;
        logger.info("===================>  System is start up as {} @ {}", CURRENT_ENV, DateUtil.getYyyyMmDdHhMmSs(startupDate));

    }

    private static void setApplicationContext(ApplicationContext context){
        applicationContext = context;
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    static {
        CURRENT_ENV = EnvType.DEV;
        APPLICATION_NAME = "CMS";
        STARTUP_DATE = System.currentTimeMillis();
        SYSTEM_START_UP_CONFIRM = false;
        SYSTEM_ENUM_CHECT_CONFIRM = false;
    }

}









